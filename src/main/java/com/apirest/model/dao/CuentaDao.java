package com.apirest.model.dao;

import com.apirest.model.entity.Cuenta;
import org.springframework.data.repository.CrudRepository;

public interface CuentaDao extends CrudRepository<Cuenta, String> {
}
