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
public class MermaDTO {

    private Long id;

    @NotNull
    private UsuarioDTO usuarioDTO;

    private List<MermaInsumoDTO> mermaInsumoDTOs;

}
