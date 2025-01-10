package com.tendencias.jsio.services;

import java.util.List;
import java.util.Map;

import com.tendencias.jsio.model.Profesor;

public interface IProfesorService {

    List<Profesor> findAll();

    Profesor save(Profesor profesor);

    Profesor findById(String profId);

    void delete(String profId);

    List<Profesor> findByDeptoId(String deptoId);

    Map<String, Long> countProfesoresByDepto();

    Profesor findByNombre(String nombre);
}

