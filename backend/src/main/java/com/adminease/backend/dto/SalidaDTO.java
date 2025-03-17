package com.adminease.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalidaDTO {

    private Long id;

    @NotNull
    private AreaDTO area;

    @NotNull
    private UsuarioDTO usuarioDTO;

    private List<SalidaInsumoDTO> salidaInsumoDTOs;

}
