package com.adminease.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompraInsumoDTO {

    private Long compraInsumoId;
    private MovimientoInsumoDTO movimientoInsumo;
    private CompraDTO compra;
    private BigDecimal precio;

}
