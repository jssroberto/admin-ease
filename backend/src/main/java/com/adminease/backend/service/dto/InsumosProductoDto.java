package com.adminease.backend.service.dto;

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
public class InsumosProductoDto {

    private Long id;

    @NotNull
    @Positive
    private Double cantidad;

    @NotNull
    private Long insumoId;

    @NotNull
    private Long productoId;

}
