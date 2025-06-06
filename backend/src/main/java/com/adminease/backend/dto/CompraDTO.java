package com.adminease.backend.dto;

import java.util.List;
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
