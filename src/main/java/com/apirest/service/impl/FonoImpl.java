package com.apirest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apirest.model.dao.FonoDao;
import com.apirest.model.entity.Fono;
import com.apirest.service.IFono;

@Service
public class FonoImpl implements IFono {

    // Inyección de dependencia del FonoDao
    @Autowired
    private FonoDao fonoDao;

    // Método para guardar un fono en la base de datos
    @Transactional
    @Override
    public Fono save(Fono fono) {
        return fonoDao.save(fono);
    }

    // Método para buscar un fono por su ID en la base de datos
    @Transactional(readOnly = true)
    @Override
    public Fono findById(Integer id) {
        return fonoDao.findById(id).orElse(null);
    }

    // Método para eliminar un fono de la base de datos
    @Transactional
    @Override
    public void delete(Fono fono) {
        fonoDao.delete(fono);
    }

    // Método para obtener todos los fonos de la base de datos
    @Transactional(readOnly = true)
    @Override
    public List<Fono> findAll() {
        return (List<Fono>) fonoDao.findAll();
    }
}
