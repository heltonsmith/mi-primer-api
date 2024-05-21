package com.apirest.service;

import java.util.List;

import com.apirest.model.entity.Fono;

public interface IFono {
    // Método para guardar un objeto Fono en la base de datos
    Fono save(Fono fono);

    // Método para buscar un objeto Fono por su ID en la base de datos
    Fono findById(Integer id);

    // Método para eliminar un objeto Fono de la base de datos
    void delete(Fono fono);

    // Método para obtener todos los objetos Fono de la base de datos
    List<Fono> findAll();
}
