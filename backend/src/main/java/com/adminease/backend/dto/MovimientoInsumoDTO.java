package com.adminease.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInsumoDTO {

    private Long movimientoInsumoId;
    private Timestamp fecha;
    private BigDecimal cantidad;
    private String tipoMovimiento;
    private Long insumoId;
}
