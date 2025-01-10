package com.tendencias.jsio.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tendencias.jsio.model.Curso;
import com.tendencias.jsio.model.Profesor;
import com.tendencias.jsio.services.ICursoService;
import com.tendencias.jsio.services.IProfesorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private ICursoService cursoService;
    
    @Autowired
    private IProfesorService profesorService;

    @GetMapping
    public ResponseEntity<?> getAllCursos() {
        List<Curso> cursos = cursoService.findAll();
        if (cursos.isEmpty()) {
            return new ResponseEntity<>("No se encontraron cursos.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCursoById(@PathVariable String id) {
        Curso curso = cursoService.findById(id);
        if (curso != null) {
            return new ResponseEntity<>(curso, HttpStatus.OK);
        }
        return new ResponseEntity<>("Curso no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> saveCurso(@Valid @RequestBody Curso curso) {
        try {
            Curso savedCurso = cursoService.save(curso);
            return new ResponseEntity<>("Curso creado exitosamente con ID: " + savedCurso.getCursoId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el curso: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurso(@PathVariable String id, @Valid @RequestBody Curso curso) {
        Curso existingCurso = cursoService.findById(id);
        if (existingCurso != null) {
            curso.setCursoId(id);
            cursoService.save(curso);
            return new ResponseEntity<>("Curso con ID: " + id + " actualizado exitosamente.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Curso no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable String id) {
        Curso existingCurso = cursoService.findById(id);
        if (existingCurso != null) {
            cursoService.delete(id);
            return new ResponseEntity<>("Curso con ID: " + id + " eliminado exitosamente.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Curso no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/profesor/{profId}")
    public ResponseEntity<?> getCursosByProfesor(@PathVariable String profId) {
        List<Curso> cursos = cursoService.findByProfesorId(profId);
        if (cursos.isEmpty()) {
            return new ResponseEntity<>("No se encontraron cursos para el profesor con ID: " + profId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Se encontraron " + cursos.size() + " cursos para el profesor con ID: " + profId, HttpStatus.OK);
    }

    @GetMapping("/count/nivel")
    public ResponseEntity<?> countCursosByNivel() {
        List<Curso> cursos = cursoService.findAll();
        Map<String, Long> countByNivel = cursos.stream()
            .collect(Collectors.groupingBy(Curso::getNivel, Collectors.counting()));

        if (countByNivel.isEmpty()) {
            return new ResponseEntity<>("No hay cursos disponibles.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Conteo de cursos por nivel: " + countByNivel, HttpStatus.OK);
    }

    @GetMapping("/{cursoId}/profesor")
    public ResponseEntity<?> getProfesorByCurso(@PathVariable String cursoId) {
        Curso curso = cursoService.findById(cursoId);
        if (curso == null) {
            return new ResponseEntity<>("Curso no encontrado con ID: " + cursoId, HttpStatus.NOT_FOUND);
        }
        Profesor profesor = profesorService.findById(curso.getProfId());
        if (profesor == null) {
            return new ResponseEntity<>("Profesor no encontrado para el curso con ID: " + cursoId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Profesor asignado al curso con ID: " + cursoId + " es: " + profesor.getNombre(), HttpStatus.OK);
    }



}
