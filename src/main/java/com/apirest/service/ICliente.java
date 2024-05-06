package com.apirest.service;


import java.util.ArrayList;

import com.apirest.model.entity.Cliente;

public interface ICliente {
    Cliente save(Cliente cliente);
    Cliente findById(Integer id);
    void delete(Cliente cliente);
    ArrayList<Cliente> findByAll();
}
