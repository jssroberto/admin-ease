package com.adminease.backend.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalidaInsumoResponse {

    private Long id;

    private Double cantidad;

    private Long insumoId;

    private String insumoNombre;

}
