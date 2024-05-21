package com.apirest.model.entity;

import java.io.Serializable;

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
import lombok.ToString;

// La anotación @Data de Lombok genera automáticamente los métodos getter, setter, equals, hashCode y toString para la clase
@Data
// La anotación @AllArgsConstructor de Lombok genera automáticamente un constructor con todos los argumentos
@AllArgsConstructor
// La anotación @NoArgsConstructor de Lombok genera automáticamente un constructor sin argumentos
@NoArgsConstructor
// La anotación @ToString de Lombok genera automáticamente un método toString para la clase
@ToString
// La anotación @Entity indica que esta clase es una entidad JPA
@Entity
// La anotación @Table especifica el nombre de la tabla de base de datos que se mapea a esta entidad
@Table(name = "fono")
public class Fono implements Serializable {

    // La anotación @Id indica que este campo es la clave primaria de la entidad
    @Id
    // La anotación @GeneratedValue especifica que el valor de este campo se generará automáticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Definición del campo "numero" en la tabla de base de datos
    private Integer numero;

    // La anotación @ManyToOne indica que esta relación es de muchos a uno con la entidad Cliente
    @ManyToOne
    // La anotación @JoinColumn especifica la columna de la tabla de base de datos que se utiliza como clave externa para esta relación
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;
}
