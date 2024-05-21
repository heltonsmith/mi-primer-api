package com.apirest.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// La anotación @Data de Lombok genera automáticamente los métodos getter, setter, equals, hashCode y toString para la clase
@Data
// La anotación @AllArgsConstructor de Lombok genera automáticamente un constructor con todos los argumentos
@AllArgsConstructor
// La anotación @NoArgsConstructor de Lombok genera automáticamente un constructor sin argumentos
@NoArgsConstructor
// La anotación @Entity indica que esta clase es una entidad JPA
@Entity
// La anotación @Table especifica el nombre de la tabla de base de datos que se mapea a esta entidad
@Table(name = "vendedor")
public class Vendedor {

    // La anotación @Id indica que este campo es la clave primaria de la entidad
    @Id
    // La anotación @Column especifica el nombre de la columna de la tabla de base de datos a la que se asigna este campo
    // y sus propiedades, como la longitud máxima de la cadena en este caso
    @Column(name = "rut", length = 30)
    private String rut;

    // Definición del campo "nombre" en la tabla de base de datos
    @Column(name = "nombre", length = 30)
    private String nombre;

    // Definición del campo "apellido" en la tabla de base de datos
    @Column(name = "apellido", length = 30)
    private String apellido;
}
