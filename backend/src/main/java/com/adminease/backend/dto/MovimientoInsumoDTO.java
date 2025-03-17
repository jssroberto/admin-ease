package com.adminease.backend.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class MovimientoInsumoDTO {

    @NotNull
    @PastOrPresent
    private LocalDateTime fecha;

    @NotNull
    @Positive
    private Double cantidad;

    @NotNull
    private InsumoDTO insumoDTO;

    // DiscriminatorColumn
    @NotBlank
    private String tipoMovimiento;
}
