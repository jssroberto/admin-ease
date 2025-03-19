package com.adminease.backend.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SalidaInsumoDto extends MovimientoInsumoDto {

    private Long salidaId;

    public SalidaInsumoDto() {
        setTipoMovimiento("SALIDA");
    }

}
