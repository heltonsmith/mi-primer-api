package com.apirest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apirest.model.dao.ClienteDao;
import com.apirest.model.dao.VentaDao;
import com.apirest.model.entity.Cliente;
import com.apirest.model.entity.Venta;
import com.apirest.service.ICliente;

@Service
public class ClienteImpl implements ICliente {

    // Inyección de dependencia del ClienteDao
    @Autowired
    private ClienteDao clienteDao;

    // Inyección de dependencia del ClienteDao
    @Autowired
    private VentaDao ventaDao;

    // Método para guardar un cliente en la base de datos
    @Transactional
    @Override
    public Cliente save(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    // Método para buscar un cliente por su ID en la base de datos
    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDao.findById(id).orElse(null);
    }

    // Método para eliminar un cliente de la base de datos
    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    // Método para obtener todos los clientes de la base de datos
    @Transactional(readOnly = true)
    @Override
    public ArrayList<Cliente> findByAll() {
        return (ArrayList<Cliente>) clienteDao.findAll();
    }

}
