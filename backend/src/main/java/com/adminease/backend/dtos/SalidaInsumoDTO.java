package com.adminease.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalidaInsumoDTO {

    private Long salidaInsumoId;
    private Long movimientoInsumoId;
    private Long salidaId;

}
