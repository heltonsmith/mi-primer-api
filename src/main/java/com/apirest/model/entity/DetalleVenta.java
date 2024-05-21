package com.apirest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "detalle_venta")
public class DetalleVenta {

    // La anotación @Id indica que este campo es la clave primaria de la entidad
    @Id
    // La anotación @GeneratedValue especifica que el valor de este campo se generará automáticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // La anotación @Column especifica el nombre de la columna de la tabla de base de datos que se mapea a este campo
    @Column(name = "id")
    private Integer id;

    // La anotación @Column especifica el nombre de la columna de la tabla de base de datos que se mapea a este campo
    @Column(name = "producto")
    private String producto;

    // La anotación @Column especifica el nombre de la columna de la tabla de base de datos que se mapea a este campo
    @Column(name = "precio")
    private Integer precio;

    // La anotación @ManyToOne indica que esta relación es de muchos a uno con la entidad Venta
    @ManyToOne
    // La anotación @JoinColumn especifica la columna de la tabla de base de datos que se utiliza como clave externa para esta relación
    // La opción nullable = false indica que el campo id_venta no puede ser nulo en la base de datos
    @JoinColumn(name = "id_venta", nullable = false)
    // La anotación @JsonBackReference se utiliza para evitar la serialización en bucle en la relación bidireccional entre Venta y DetalleVenta
    @JsonBackReference
    private Venta venta;
}
