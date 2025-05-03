package com.adminease.backend.api.dto.response;

import com.adminease.backend.dto.InsumoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompraInsumoResponse {

    private InsumoDTO insumo;

    private Double precioUnitario;

    private Double cantidad;
}

