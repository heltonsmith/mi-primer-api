package com.apirest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FonoResponseDTO {
    private Integer code;
    private Integer id;
    private Integer numero;
    private String cliente;
}
