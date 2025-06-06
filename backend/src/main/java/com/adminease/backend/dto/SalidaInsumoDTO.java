package com.adminease.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SalidaInsumoDTO extends MovimientoInsumoDTO {

    @NotNull
    private SalidaDTO salidaDTO;

    public SalidaInsumoDTO() {
        setTipoMovimiento("SALIDA");
    }

}
