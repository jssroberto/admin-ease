package com.adminease.backend.dto;

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
public class InsumosProductoDTO {

    private Long id;

    @NotNull
    @Positive
    private Double cantidad;

    @NotNull
    private InsumoDTO insumoDTO;

    @NotNull
    private ProductoDTO productoDTO;

}

