package com.adminease.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompraInsumoDTO extends MovimientoInsumoDTO {

    @NotNull
    @Positive
    private Double precio;

    @NotNull
    private CompraDTO compraDTO;

    public CompraInsumoDTO() {
        setTipoMovimiento("COMPRA");
    }

}
