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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tendencias.jsio.model.Profesor;
import com.tendencias.jsio.services.IProfesorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private IProfesorService profesorService;

    @GetMapping
    public ResponseEntity<?> getAllProfesores() {
        List<Profesor> profesores = profesorService.findAll();
        if (profesores.isEmpty()) {
            return new ResponseEntity<>("No se encontraron profesores.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profesores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfesorById(@PathVariable String id) {
        Profesor profesor = profesorService.findById(id);
        if (profesor != null) {
            return new ResponseEntity<>(profesor, HttpStatus.OK);
        }
        return new ResponseEntity<>("Profesor no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> saveProfesor(@Valid @RequestBody Profesor profesor) {
        try {
            Profesor savedProfesor = profesorService.save(profesor);
            return new ResponseEntity<>("Profesor creado exitosamente con ID: " + savedProfesor.getProfId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el profesor: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfesor(@PathVariable String id, @Valid @RequestBody Profesor profesor) {
        Profesor existingProfesor = profesorService.findById(id);
        if (existingProfesor != null) {
            profesor.setProfId(id);
            profesorService.save(profesor);
            return new ResponseEntity<>("Profesor con ID: " + id + " actualizado exitosamente.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Profesor no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfesor(@PathVariable String id) {
        Profesor existingProfesor = profesorService.findById(id);
        if (existingProfesor != null) {
            profesorService.delete(id);
            return new ResponseEntity<>("Profesor con ID: " + id + " eliminado exitosamente.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Profesor no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/departamento/{deptoId}")
    public ResponseEntity<?> getProfesoresByDepto(@PathVariable String deptoId) {
        List<Profesor> profesores = profesorService.findByDeptoId(deptoId);
        if (profesores.isEmpty()) {
            return new ResponseEntity<>("No se encontraron profesores para el departamento con ID: " + deptoId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Se encontraron " + profesores.size() + " profesores para el departamento con ID: " + deptoId, HttpStatus.OK);
    }

    @GetMapping("/count/profesores")
    public ResponseEntity<?> getDepartamentosWithMinProfesores(@RequestParam int minProfesores) {
        Map<String, Long> countByDepto = profesorService.countProfesoresByDepto();
        Map<String, Long> filteredDeptos = countByDepto.entrySet().stream()
            .filter(entry -> entry.getValue() >= minProfesores)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (filteredDeptos.isEmpty()) {
            return new ResponseEntity<>("No se encontraron departamentos con más de " + minProfesores + " profesores.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Conteo de departamentos con más de " + minProfesores + " profesores: " + filteredDeptos, HttpStatus.OK);
    }
}


