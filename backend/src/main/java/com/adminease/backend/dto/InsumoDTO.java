package com.adminease.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsumoDTO {

    private Long id;

    @NotBlank
    @Size(min = 12, max = 13)
    private String codigo;

    @NotBlank
    private String nombre;

    @NotNull
    @PositiveOrZero
    private Double stock;

    @NotNull
    private UnidadMedidaDTO unidadMedidaDTO;

    @NotNull
    private CategoriaInsumoDTO categoriaInsumoDTO;

    private List<InsumosProductoDTO> insumosProductoDTOS;

    private List<MovimientoInsumoDTO> movimientoInsumoDTOS;

}

