package com.apirest.model.dao;

import com.apirest.model.entity.Cliente;
import com.apirest.model.entity.Venta;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// Esta interfaz extiende CrudRepository y está parametrizada con la clase Venta y el tipo de su identificador (Integer)
public interface VentaDao extends CrudRepository<Venta, Integer> {

    // List para traer lista de ventas por cliente
    List<Venta> findByCliente(Cliente cliente);
}
