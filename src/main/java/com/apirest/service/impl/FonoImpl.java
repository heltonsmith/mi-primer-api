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

    @Autowired
    private FonoDao fonoDao;

    @Transactional
    @Override
    public Fono save(Fono fono) {
        return fonoDao.save(fono);
    }

    @Transactional(readOnly = true)
    @Override
    public Fono findById(Integer id) {
        return fonoDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Fono fono) {
        fonoDao.delete(fono);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Fono> findAll() {
        return (List<Fono>) fonoDao.findAll();
    }
}
