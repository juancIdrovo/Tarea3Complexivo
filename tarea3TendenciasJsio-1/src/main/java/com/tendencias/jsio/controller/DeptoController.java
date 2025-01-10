package com.tendencias.jsio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tendencias.jsio.model.Depto;
import com.tendencias.jsio.services.IDeptoService;
import com.tendencias.jsio.services.IProfesorService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departamentos")
public class DeptoController {

    @Autowired
    private IDeptoService deptoService;
    private IProfesorService profesorService;

    @GetMapping
    public ResponseEntity<?> getAllDeptos() {
        List<Depto> deptos = deptoService.findAll();
        if (deptos.isEmpty()) {
            return new ResponseEntity<>("No se encontraron departamentos.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deptos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeptoById(@PathVariable String id) {
        Depto depto = deptoService.findById(id);
        if (depto != null) {
            return new ResponseEntity<>(depto, HttpStatus.OK);
        }
        return new ResponseEntity<>("Departamento no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> saveDepto(@Valid @RequestBody Depto depto) {
        try {
            Depto savedDepto = deptoService.save(depto);
            return new ResponseEntity<>("Departamento creado exitosamente con ID: " + savedDepto.getDeptoId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el departamento: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepto(@PathVariable String id, @Valid @RequestBody Depto depto) {
        Depto existingDepto = deptoService.findById(id);
        if (existingDepto != null) {
            depto.setDeptoId(id);
            deptoService.save(depto);
            return new ResponseEntity<>("Departamento con ID: " + id + " actualizado exitosamente.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Departamento no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepto(@PathVariable String id) {
        Depto existingDepto = deptoService.findById(id);
        if (existingDepto != null) {
            deptoService.delete(id);
            return new ResponseEntity<>("Departamento con ID: " + id + " eliminado con éxito.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Departamento no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
    }


}



