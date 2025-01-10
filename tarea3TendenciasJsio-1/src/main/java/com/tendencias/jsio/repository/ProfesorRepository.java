package com.tendencias.jsio.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tendencias.jsio.model.Profesor;


@Repository
public interface ProfesorRepository extends MongoRepository<Profesor, String> {
   
    Profesor findByNombre(String nombre);
    
    List<Profesor> findByDeptoId(String deptoId);

}
