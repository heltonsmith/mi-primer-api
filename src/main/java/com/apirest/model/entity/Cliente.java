package com.apirest.model.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// La anotación @Data de Lombok genera automáticamente los métodos getter, setter, equals, hashCode y toString para la clase
@Data
// La anotación @AllArgsConstructor de Lombok genera automáticamente un constructor con todos los argumentos
@AllArgsConstructor
// La anotación @NoArgsConstructor de Lombok genera automáticamente un constructor sin argumentos
@NoArgsConstructor
// La anotación @ToString de Lombok genera automáticamente el método toString para la clase
@ToString
// La anotación @Entity indica que esta clase es una entidad JPA
@Entity
// La anotación @Table especifica el nombre de la tabla de base de datos que se mapea a esta entidad
@Table(name = "usuario")
public class Cliente implements Serializable {

    // La anotación @Id indica que este campo es la clave primaria de la entidad
    @Id
    // La anotación @Column especifica el nombre de la columna de la tabla de base de datos que se mapea a este campo
    @Column(name = "id_cliente")
    // La anotación @GeneratedValue especifica la estrategia de generación de valores para la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    // La anotación @Column especifica el nombre de la columna de la tabla de base de datos que se mapea a este campo
    @Column(name = "nombre")
    private String nombre;

    // La anotación @Column especifica el nombre de la columna de la tabla de base de datos que se mapea a este campo
    @Column(name = "apellido")
    private String apellido;

    // La anotación @Column especifica el nombre de la columna de la tabla de base de datos que se mapea a este campo
    @Column(name = "correo")
    private String correo;

    // La anotación @Column especifica el nombre de la columna de la tabla de base de datos que se mapea a este campo
    @Column(name = "fecha_registro")
    private Date fechaRegistro;
}
