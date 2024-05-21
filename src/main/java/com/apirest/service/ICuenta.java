package com.apirest.service;

import com.apirest.model.entity.Cuenta;

public interface ICuenta {
    // Método para guardar una cuenta en la base de datos
    Cuenta save(Cuenta cuenta);

    // Método para buscar una cuenta por su usuario en la base de datos
    Cuenta findByUsuario(String usuario);

    // Método para eliminar una cuenta de la base de datos
    void delete(Cuenta cuenta);
}
