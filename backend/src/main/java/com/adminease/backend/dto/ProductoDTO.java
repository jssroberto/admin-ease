package com.adminease.backend.dto;

import jakarta.validation.constraints.NotBlank;
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
public class ProductoDTO {

    private Long id;

    @NotBlank
    private String nombre;

    @NotNull
    private CategoriaProductoDTO categoriaProductoDTO;

    @NotNull
    private List<InsumosProductoDTO> insumosProductoDTOs;

}