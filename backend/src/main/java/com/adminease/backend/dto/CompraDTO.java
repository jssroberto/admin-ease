package com.adminease.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompraDTO {

    private Long id;

    @NotNull
    @Positive
    private Double total;

    @NotNull
    private ProveedorDTO proveedorDTO;

    @NotNull
    private UsuarioDTO usuarioDTO;

    private List<CompraInsumoDTO> compraInsumoDTOs;

}
