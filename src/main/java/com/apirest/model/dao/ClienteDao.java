package com.apirest.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.apirest.model.entity.Cliente;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {

}
