package com.tendencias.jsio.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tendencias.jsio.model.Profesor;
import com.tendencias.jsio.repository.ProfesorRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements IProfesorService {

    @Autowired
    private ProfesorRepository profeRep;

    @Override
    @Transactional(readOnly = true)
    public List<Profesor> findAll() {
        return profeRep.findAll();
    }

    @Override
    @Transactional
    public Profesor save(Profesor profesor) {
        return profeRep.save(profesor);
    }

    @Override
    @Transactional(readOnly = true)
    public Profesor findById(String profId) { // Cambiado a String
        return profeRep.findById(profId).orElse(null);
    }

    @Override
    @Transactional
    public void delete(String profId) { // Cambiado a String
        profeRep.deleteById(profId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Profesor> findByDeptoId(String deptoId) {
        return profeRep.findByDeptoId(deptoId);
    }

    
    @Override
    public Map<String, Long> countProfesoresByDepto() {
        List<Profesor> profesores = profeRep.findAll();
        return profesores.stream()
                .collect(Collectors.groupingBy(
                        Profesor::getDeptoId, 
                        Collectors.counting()
                ));
    }


    @Override
    @Transactional(readOnly = true)
    public Profesor findByNombre(String nombre) {
        return profeRep.findByNombre(nombre);
    }
}


