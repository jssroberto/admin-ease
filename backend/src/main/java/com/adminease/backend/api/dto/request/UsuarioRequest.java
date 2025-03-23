package com.adminease.backend.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    private String contrasena;

    @NotNull
    private Long rolId;

}
