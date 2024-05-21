package com.apirest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// La anotación @Data de Lombok genera automáticamente los métodos getter, setter, equals, hashCode y toString para la clase
@Data
// La anotación @AllArgsConstructor de Lombok genera automáticamente un constructor con todos los argumentos
@AllArgsConstructor
// La anotación @NoArgsConstructor de Lombok genera automáticamente un constructor sin argumentos
@NoArgsConstructor
public class FonoResponseDTO {
    // Código de estado de la respuesta
    private Integer code;
    // Identificador del objeto Fono creado
    private Integer id;
    // Número de teléfono asociado al objeto Fono
    private Integer numero;
    // Nombre del cliente asociado al objeto Fono
    private String cliente;
}
