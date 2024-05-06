package com.apirest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apirest.model.dao.ClienteDao;
import com.apirest.model.entity.Cliente;
import com.apirest.service.ICliente;


@Service
public class ClienteImpl implements ICliente{

    @Autowired
    private ClienteDao clienteDao;

    @Transactional
    @Override
    public Cliente save(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public ArrayList<Cliente> findByAll() {
        return (ArrayList<Cliente> )clienteDao.findAll();
    }

}
