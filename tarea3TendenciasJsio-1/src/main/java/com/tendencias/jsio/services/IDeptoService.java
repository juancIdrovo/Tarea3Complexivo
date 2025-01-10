package com.tendencias.jsio.services;

import java.util.List;

import com.tendencias.jsio.model.Depto;

public interface IDeptoService {

    List<Depto> findAll();

    Depto save(Depto depto);

    Depto findById(String deptoId); 

    void delete(String deptoId); 
}


