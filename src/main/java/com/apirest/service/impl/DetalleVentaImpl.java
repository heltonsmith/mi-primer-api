package com.apirest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apirest.model.dao.ClienteDao;
import com.apirest.model.dao.DetalleVentaDao;
import com.apirest.model.dao.VentaDao;
import com.apirest.model.entity.Cliente;
import com.apirest.model.entity.DetalleVenta;
import com.apirest.model.entity.Venta;
import com.apirest.service.IDetalleVenta;

@Service
public class DetalleVentaImpl implements IDetalleVenta {

    // Inyección de dependencia del DetalleVentaDao
    @Autowired
    private DetalleVentaDao detalleDao;

    // Inyección de dependencia del ClienteDao
    @Autowired
    private ClienteDao clienteDao;

    // Inyección de dependencia del VentaDao
    @Autowired
    private VentaDao ventaDao;

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

    // Método para mostrar todos los detalles de las ventas de la bd
    @Override
    @Transactional(readOnly = true)
    public ArrayList<Map<String, Object>> mostrarVentas(Integer id) {
        // Primero, busca el cliente por su ID para asegurarte de que existe
        Cliente cliente = clienteDao.findById(id).orElse(null);
        if (cliente == null) {
            // Manejar el caso en que el cliente no exista
            // Por ejemplo, lanzar una excepción o devolver una lista vacía
            return new ArrayList<>();
        }
        
        // Luego, busca todas las ventas asociadas a ese cliente
        List<Venta> ventasCliente = ventaDao.findByCliente(cliente);
        
        // Ahora, crea una lista para almacenar los detalles de ventas
        ArrayList<Map<String, Object>> ventasConDetalle = new ArrayList<>();
        
        // Itera sobre cada venta y agrega sus detalles a la lista de ventas
        for (Venta venta : ventasCliente) {
            List<DetalleVenta> detalles = venta.getDetalleVentas();
            for (DetalleVenta detalle : detalles) {
                Map<String, Object> ventaConDetalle = new HashMap<>();
                ventaConDetalle.put("Cliente", venta.getCliente().getNombre() + " " + venta.getCliente().getApellido());
                ventaConDetalle.put("venta_id", venta.getId());
                ventaConDetalle.put("detalle_id", detalle.getId());
                ventaConDetalle.put("producto", detalle.getProducto());
                ventaConDetalle.put("precio", detalle.getPrecio());
                ventasConDetalle.add(ventaConDetalle);
            }
        }
        
        // Devuelve la lista de detalles de ventas filtrados por el ID del cliente
        return ventasConDetalle;

    }
}
