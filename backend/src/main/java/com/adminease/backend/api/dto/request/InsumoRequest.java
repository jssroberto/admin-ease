package com.adminease.backend.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InsumoRequest {

    @NotBlank
    @Size(min = 12, max = 13)
    private String codigo;

    @NotBlank
    private String nombre;

    @PositiveOrZero
    private Double stock;

    @NotNull
    private Long unidadMedidaId;

    @NotNull
    private Long categoriaInsumoId;

}
