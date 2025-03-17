package com.adminease.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RolDTO {

    private Long id;

    @NotBlank
    private String nombre;

    private List<UsuarioDTO> usuarioDTOS;

    private List<PermisoDTO> permisoDTOS;

}
