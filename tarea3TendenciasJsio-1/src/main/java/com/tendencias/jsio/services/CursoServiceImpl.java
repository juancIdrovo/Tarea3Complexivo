package com.tendencias.jsio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tendencias.jsio.model.Curso;
import com.tendencias.jsio.repository.CursoRepository;

@Service
public class CursoServiceImpl implements ICursoService {
	
	@Autowired
	private CursoRepository cursoRepository;

    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Curso findById(String cursoId) { 
        return cursoRepository.findById(cursoId).orElse(null);
    }

    @Override
    public void delete(String cursoId) { 
        cursoRepository.deleteById(cursoId);
    }

	@Override
	public List<Curso> findByNivel(String nivel) {
		// TODO Auto-generated method stub
		return cursoRepository.findByNivel(nivel);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Curso> findByProfesorId(String profId) {
	    return cursoRepository.findByProfId(profId);
	}

}
