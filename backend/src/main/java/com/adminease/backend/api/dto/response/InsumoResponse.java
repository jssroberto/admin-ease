package com.adminease.backend.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InsumoResponse {

    private Long id;

    private String codigo;

    private String nombre;

    private Double stock;

    private Long unidadMedidaId;

    private Long categoriaInsumoId;

}
