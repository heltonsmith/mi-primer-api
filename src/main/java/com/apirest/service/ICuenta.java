package com.apirest.service;

import com.apirest.model.entity.Cuenta;

public interface ICuenta {
    Cuenta save(Cuenta cuenta);
    Cuenta findByUsuario(String usuario);
    void delete(Cuenta cuenta);
}
