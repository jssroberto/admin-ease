package com.adminease.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MermaInsumoDTO {

    private Long mermaInsumoId;
    private Long movimientoInsumoId;
    private Long mermaId;
    private String razon;
}
