package com.adminease.backend.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorResponse {

    private Long id;
    private String nombre;
    private String telefono;
    private String correo;
}
