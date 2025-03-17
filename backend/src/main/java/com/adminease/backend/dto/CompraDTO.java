package com.adminease.backend.dto;

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

    private Long compraId;
    private BigDecimal total;
    private ProveedorDTO proveedor;  // Para incluir los detalles del proveedor
    private UsuarioDTO usuario;      // Para incluir los detalles del usuario
    private List<CompraInsumoDTO> compraInsumos; // Para incluir los detalles de los insumos

}
