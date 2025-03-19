package com.adminease.backend.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class MovimientoInsumoDto {

    @NotNull
    @Positive
    private Double cantidad;

    @NotNull
    private Long insumoId;

    // DiscriminatorColumn
    @NotBlank
    private String tipoMovimiento;
}
