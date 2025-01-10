package com.tendencias.jsio.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tendencias.jsio.model.Depto;
import com.tendencias.jsio.repository.DeptoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeptoServiceImpl implements IDeptoService {

    @Autowired
    private DeptoRepository deptoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Depto> findAll() {
        return deptoRepository.findAll();
    }

    @Override
    @Transactional
    public Depto save(Depto depto) {
        return deptoRepository.save(depto);
    }

    @Override
    @Transactional(readOnly = true)
    public Depto findById(String deptoId) { 
        return deptoRepository.findById(deptoId).orElse(null);
    }

    @Override
    @Transactional
    public void delete(String deptoId) { 
        deptoRepository.deleteById(deptoId);
    }
}


