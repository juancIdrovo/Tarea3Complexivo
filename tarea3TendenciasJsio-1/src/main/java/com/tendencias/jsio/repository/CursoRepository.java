package com.tendencias.jsio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tendencias.jsio.model.Curso;

import java.util.List;

@Repository
public interface CursoRepository extends MongoRepository<Curso, String> {
    
	List<Curso> findByNivel(String nivel);
	
	List<Curso> findByProfId(String profId);

}
