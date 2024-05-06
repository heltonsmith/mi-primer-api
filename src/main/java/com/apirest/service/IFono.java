package com.apirest.service;

import java.util.List;

import com.apirest.model.entity.Fono;

public interface IFono {
    Fono save(Fono fono);
    Fono findById(Integer id);
    void delete(Fono fono);
    List<Fono> findAll();
}
