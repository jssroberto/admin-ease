package com.adminease.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsumosProductoDTO {

    private Long id;
    private Long insumoId;
    private Long productoId;
    private BigDecimal cantidad;




}

