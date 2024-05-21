package com.apirest.service.impl;

import com.apirest.model.dao.CuentaDao;
import com.apirest.model.entity.Cuenta;
import com.apirest.service.ICuenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuentaImpl implements ICuenta {

    // Inyección de dependencia del CuentaDao
    @Autowired
    private CuentaDao cuentaDao;

    // Método para guardar una cuenta en la base de datos
    @Transactional
    @Override
    public Cuenta save(Cuenta cuenta) {
        return cuentaDao.save(cuenta);
    }

    // Método para buscar una cuenta por su usuario en la base de datos
    @Transactional(readOnly = true)
    @Override
    public Cuenta findByUsuario(String usuario) {
        return cuentaDao.findById(usuario).orElse(null);
    }

    // Método para eliminar una cuenta de la base de datos
    @Transactional
    @Override
    public void delete(Cuenta cuenta) {
        cuentaDao.delete(cuenta);
    }
}
