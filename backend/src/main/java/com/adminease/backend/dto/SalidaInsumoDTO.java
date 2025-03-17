package com.adminease.backend.dto;

import com.adminease.backend.model.SalidaInsumo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SalidaInsumoDTO extends MovimientoInsumoDTO{

    @NotNull
    private SalidaDTO salidaDTO;

    public SalidaInsumoDTO() {
        setTipoMovimiento("SALIDA");
    }

}
