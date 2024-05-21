package com.apirest.model.dao;

import com.apirest.model.entity.DetalleVenta;
import org.springframework.data.repository.CrudRepository;

// Esta interfaz extiende CrudRepository y está parametrizada con la clase DetalleVenta y el tipo de su identificador (Integer)
public interface DetalleVentaDao extends CrudRepository<DetalleVenta, Integer> {
}
