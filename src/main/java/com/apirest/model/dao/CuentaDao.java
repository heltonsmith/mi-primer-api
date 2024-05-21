package com.apirest.model.dao;

import com.apirest.model.entity.Cuenta;
import org.springframework.data.repository.CrudRepository;

// Esta interfaz extiende CrudRepository y está parametrizada con la clase Cuenta y el tipo de su identificador (String)
public interface CuentaDao extends CrudRepository<Cuenta, String> {
}
