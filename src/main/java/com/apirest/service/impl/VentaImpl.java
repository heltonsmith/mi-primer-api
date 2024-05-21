package com.apirest.service.impl;

import com.apirest.model.dao.VentaDao;
import com.apirest.model.entity.Cliente;
import com.apirest.model.entity.DetalleVenta;
import com.apirest.model.entity.Vendedor;
import com.apirest.model.entity.Venta;
import com.apirest.service.ICliente;
import com.apirest.service.IVendedor;
import com.apirest.service.IVenta;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaImpl implements IVenta {

    @Autowired
    private VentaDao ventaDao;

    @Autowired
    private ICliente clienteDao;

    @Autowired
    private IVendedor vendedorDao;

    // Método para guardar una venta en la base de datos
    @Override
    @Transactional
    public Venta save(Venta venta) {
        // Asegurarse de que el cliente está en la base de datos
        Cliente cliente = clienteDao.findById(venta.getCliente().getIdCliente());
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente no encontrado");
        }
        venta.setCliente(cliente);

        // Asegurarse de que el vendedor está en la base de datos
        Vendedor vendedor = vendedorDao.findByRut(venta.getVendedor().getRut());
        if (vendedor == null) {
            throw new EntityNotFoundException("Vendedor no encontrado");
        }
        venta.setVendedor(vendedor);

        // Asignar la venta a cada detalle de venta
        for (DetalleVenta detalle : venta.getDetalleVentas()) {
            detalle.setVenta(venta);
        }

        return ventaDao.save(venta);
    }

    // Método para buscar una venta por su ID en la base de datos
    @Transactional(readOnly = true)
    @Override
    public Venta findById(Integer id) {
        return ventaDao.findById(id).orElse(null);
    }

    // Método para eliminar una venta de la base de datos
    @Transactional
    @Override
    public void delete(Venta venta) {
        ventaDao.delete(venta);
    }
}
