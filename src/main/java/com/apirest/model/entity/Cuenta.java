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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuenta")
public class Cuenta {

    @Id
    @Column(name = "usuario", length = 45)
    private String usuario;

    @Column(name = "clave", length = 45)
    private String clave;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
