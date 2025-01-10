package com.tendencias.jsio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tendencias.jsio.model.Depto;


@Repository
public interface DeptoRepository extends MongoRepository<Depto, String> {
    
    Depto findByNombre(String nombre);

}
