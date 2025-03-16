package com.adminease.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsumoDTO {

    private Long insumoId;
    private String codigo;
    private String nombre;
    private String unidadMedida;
    private Integer stock;
    private String categoria;
    private List<InsumosProductoDTO> productosInsumo;
    private List<MovimientoInsumoDTO> movimientosInsumo;


}

