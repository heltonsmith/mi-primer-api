package com.apirest.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "venta")
public class Venta {

    // La anotación @Id indica que este campo es la clave primaria de la entidad
    @Id
    // La anotación @GeneratedValue especifica que el valor de esta columna se genera automáticamente por la base de datos
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // La anotación @Column especifica el nombre de la columna de la tabla de base de datos a la que se asigna este campo
    @Column(name = "id")
    private Integer id;

    // La anotación @ManyToOne indica que esta entidad tiene una relación muchos a uno con la entidad Cliente
    @ManyToOne
    // La anotación @JoinColumn especifica el nombre de la columna de la tabla de base de datos que se utiliza como clave externa
    // para mapear la relación con la entidad Cliente
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    // La anotación @ManyToOne indica que esta entidad tiene una relación muchos a uno con la entidad Vendedor
    @ManyToOne
    // La anotación @JoinColumn especifica el nombre de la columna de la tabla de base de datos que se utiliza como clave externa
    // para mapear la relación con la entidad Vendedor
    @JoinColumn(name = "rut_vendedor", nullable = false)
    private Vendedor vendedor;

    // La anotación @OneToMany indica que esta entidad tiene una relación uno a muchos con la entidad DetalleVenta
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    // La anotación @JsonManagedReference se utiliza para evitar problemas de referencia circular al serializar la entidad
    // Venta a JSON
    @JsonManagedReference
    private List<DetalleVenta> detalleVentas = new ArrayList<>();

    // Método para agregar un detalle de venta a la lista de detalles de venta de esta venta
    public void addDetalleVenta(DetalleVenta detalleVenta) {
        detalleVentas.add(detalleVenta);
        detalleVenta.setVenta(this);
    }

    // Método para eliminar un detalle de venta de la lista de detalles de venta de esta venta
    public void removeDetalleVenta(DetalleVenta detalleVenta) {
        detalleVentas.remove(detalleVenta);
        detalleVenta.setVenta(null);
    }
}
