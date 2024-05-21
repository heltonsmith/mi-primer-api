package com.apirest.service;

import com.apirest.model.entity.Vendedor;

public interface IVendedor {
    // Método para guardar un objeto Vendedor en la base de datos
    Vendedor save(Vendedor vendedor);

    // Método para buscar un objeto Vendedor por su Rut en la base de datos
    Vendedor findByRut(String rut);

    // Método para eliminar un objeto Vendedor de la base de datos
    void delete(Vendedor vendedor);
}
