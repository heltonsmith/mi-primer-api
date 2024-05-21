package com.apirest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apirest.model.dao.DetalleVentaDao;
import com.apirest.model.entity.DetalleVenta;
import com.apirest.service.IDetalleVenta;

@Service
public class DetalleVentaImpl implements IDetalleVenta {

    // Inyección de dependencia del DetalleVentaDao
    @Autowired
    private DetalleVentaDao detalleDao;

    // Método para eliminar un detalle de venta de la base de datos
    @Transactional
    @Override
    public void delete(DetalleVenta venta) {
        detalleDao.delete(venta);
    }

    // Método para buscar un detalle de venta por su ID en la base de datos
    @Transactional(readOnly = true)
    @Override
    public DetalleVenta findById(Integer id) {
        return detalleDao.findById(id).orElse(null);
    }

    // Método para guardar un detalle de venta en la base de datos
    @Transactional
    @Override
    public DetalleVenta save(DetalleVenta venta) {
        return detalleDao.save(venta);
    }
}
