package com.adminease.backend.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompraInsumoRequest {

    @NotNull
    private Long insumoId;

    @NotNull
    @Positive
    private Double cantidad;

    @NotNull
    @Positive
    private Double precioUnitario;
}
