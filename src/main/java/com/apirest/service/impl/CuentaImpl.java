package com.apirest.service.impl;

import com.apirest.model.dao.CuentaDao;
import com.apirest.model.entity.Cuenta;
import com.apirest.service.ICuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuentaImpl implements ICuenta {

    @Autowired
    private CuentaDao cuentaDao;

    @Transactional
    @Override
    public Cuenta save(Cuenta cuenta) {
        return cuentaDao.save(cuenta);
    }

    @Transactional(readOnly = true)
    @Override
    public Cuenta findByUsuario(String usuario) {
        return cuentaDao.findById(usuario).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cuenta cuenta) {
        cuentaDao.delete(cuenta);
    }
}
