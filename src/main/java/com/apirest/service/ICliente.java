package com.apirest.service;

import java.util.ArrayList;

import com.apirest.model.entity.Cliente;
import com.apirest.model.entity.DetalleVenta;

public interface ICliente {
    // Método para guardar un cliente en la base de datos
    Cliente save(Cliente cliente);

    // Método para buscar un cliente por su ID en la base de datos
    Cliente findById(Integer id);

    // Método para eliminar un cliente de la base de datos
    void delete(Cliente cliente);

    // Método para obtener todos los clientes de la base de datos
    ArrayList<Cliente> findByAll();

    // Método para obtener todos las ventas por el id del cliente de la base de datos
    ArrayList<DetalleVenta> mostrarVentas(Integer id);
}
