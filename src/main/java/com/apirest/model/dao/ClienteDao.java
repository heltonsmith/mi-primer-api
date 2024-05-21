package com.apirest.model.dao;

import org.springframework.data.repository.CrudRepository;
import com.apirest.model.entity.Cliente;

// Esta interfaz extiende CrudRepository, que proporciona operaciones CRUD básicas para la entidad Cliente
public interface ClienteDao extends CrudRepository<Cliente, Integer> {
}

