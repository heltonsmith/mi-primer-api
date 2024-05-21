package com.apirest.service.impl;

import com.apirest.model.dao.VendedorDao;
import com.apirest.model.entity.Vendedor;
import com.apirest.service.IVendedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VendedorImpl implements IVendedor {

    // Inyección de dependencia del VendedorDao
    @Autowired
    private VendedorDao vendedorDao;

    // Método para guardar un vendedor en la base de datos
    @Transactional
    @Override
    public Vendedor save(Vendedor vendedor) {
        return vendedorDao.save(vendedor);
    }

    // Método para buscar un vendedor por su rut en la base de datos
    @Transactional(readOnly = true)
    @Override
    public Vendedor findByRut(String rut) {
        return vendedorDao.findById(rut).orElse(null);
    }

    // Método para eliminar un vendedor de la base de datos
    @Transactional
    @Override
    public void delete(Vendedor vendedor) {
        vendedorDao.delete(vendedor);
    }
}
