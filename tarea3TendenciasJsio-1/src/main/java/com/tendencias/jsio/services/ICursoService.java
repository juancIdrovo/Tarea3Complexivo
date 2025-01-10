 package com.tendencias.jsio.services;
import java.util.List;

import com.tendencias.jsio.model.Curso;

public interface ICursoService  {
	
	List<Curso> findAll();
	
    Curso save(Curso curso);
    
    Curso findById(String cursoId);
 
    void delete(String cursoId); 
	
	List<Curso> findByNivel(String nivel);
	
	List<Curso> findByProfesorId(String profId);

}
