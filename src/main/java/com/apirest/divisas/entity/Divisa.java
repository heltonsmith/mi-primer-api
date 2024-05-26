package com.apirest.divisas.entity;

import java.time.OffsetDateTime;

public class Divisa {
    // Campos privados de la clase
    private String codigo; // Código de la divisa (por ejemplo, "USD" para el dólar estadounidense)
    private String nombre; // Nombre de la divisa (por ejemplo, "Dólar estadounidense")
    private String unidadMedida; // Unidad de medida de la divisa (por ejemplo, "Pesos" o "Dólar")
    private OffsetDateTime fecha; // Fecha de la cotización de la divisa
    private double valor; // Valor de la divisa

    // Métodos Getters y Setters
    // Método getter para obtener el código de la divisa
    public String getCodigo() {
        return codigo;
    }

    // Método setter para establecer el código de la divisa
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Método getter para obtener el nombre de la divisa
    public String getNombre() {
        return nombre;
    }

    // Método setter para establecer el nombre de la divisa
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método getter para obtener la unidad de medida de la divisa
    public String getUnidadMedida() {
        return unidadMedida;
    }

    // Método setter para establecer la unidad de medida de la divisa
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    // Método getter para obtener la fecha de la cotización de la divisa
    public OffsetDateTime getFecha() {
        return fecha;
    }

    // Método setter para establecer la fecha de la cotización de la divisa
    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    // Método getter para obtener el valor de la divisa
    public double getValor() {
        return valor;
    }

    // Método setter para establecer el valor de la divisa
    public void setValor(double valor) {
        this.valor = valor;
    }
}
