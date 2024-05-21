package com.apirest.service;

import java.util.List;

import com.apirest.model.entity.Venta;

public interface IVenta {
    // Método para guardar una venta en la base de datos
    public Venta save(Venta venta);

    // Método para buscar una venta por su ID en la base de datos
    public Venta findById(Integer id);

    // Método para eliminar una venta de la base de datos
    public void delete(Venta venta);
}
