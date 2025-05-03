package com.adminease.backend.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    @Size(min = 10, max = 10)
    private String telefono;

    @NotBlank
    @Email
    private String correo;

}
