package com.apirest.service;

import java.util.ArrayList;
import java.util.Map;

import com.apirest.model.entity.DetalleVenta;

public interface IDetalleVenta {
    // Método para guardar un detalle de venta en la base de datos
    DetalleVenta save(DetalleVenta venta);

    // Método para buscar un detalle de venta por su ID en la base de datos
    DetalleVenta findById(Integer id);

    // Método para eliminar un detalle de venta de la base de datos
    void delete(DetalleVenta venta);

    // Método para traer una lista de detalle de venta de la base de datos
    ArrayList<Map<String, Object>> mostrarVentas(Integer id);
}
