package com.apirest.model.dao;

import org.springframework.data.repository.CrudRepository;
import com.apirest.model.entity.Fono;

// Esta interfaz extiende CrudRepository y está parametrizada con la clase Fono y el tipo de su identificador (Integer)
public interface FonoDao extends CrudRepository<Fono, Integer> {
}
