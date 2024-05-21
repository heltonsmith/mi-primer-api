package com.apirest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// La anotación @Data de Lombok genera automáticamente los métodos getter, setter, equals, hashCode y toString para la clase
@Data
// La anotación @AllArgsConstructor de Lombok genera automáticamente un constructor con todos los argumentos
@AllArgsConstructor
// La anotación @NoArgsConstructor de Lombok genera automáticamente un constructor sin argumentos
@NoArgsConstructor
// La anotación @Entity indica que esta clase es una entidad JPA
@Entity
// La anotación @Table especifica el nombre de la tabla de base de datos que se mapea a esta entidad
@Table(name = "cuenta")
public class Cuenta {

    // La anotación @Id indica que este campo es la clave primaria de la entidad
    @Id
    // La anotación @Column especifica el nombre de la columna de la tabla de base de datos que se mapea a este campo
    @Column(name = "usuario", length = 45)
    private String usuario;

    // La anotación @Column especifica el nombre de la columna de la tabla de base de datos que se mapea a este campo
    @Column(name = "clave", length = 45)
    private String clave;

    // La anotación @ManyToOne indica que esta relación es de muchos a uno con la entidad Cliente
    @ManyToOne
    // La anotación @JoinColumn especifica la columna de la tabla de base de datos que se utiliza como clave externa para esta relación
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
