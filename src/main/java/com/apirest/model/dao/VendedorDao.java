package com.apirest.model.dao;

import com.apirest.model.entity.Vendedor;
import org.springframework.data.repository.CrudRepository;

// Esta interfaz extiende CrudRepository y está parametrizada con la clase Vendedor y el tipo de su identificador (String)
public interface VendedorDao extends CrudRepository<Vendedor, String> {
}
