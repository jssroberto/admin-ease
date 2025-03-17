package com.adminease.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDTO {

    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    @Size(min = 10, max = 10)
    private String telefono;

    @NotBlank
    @Email
    private String correo;

}
