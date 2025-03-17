package com.adminease.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MermaInsumoDTO extends MovimientoInsumoDTO{

    @NotNull
    private RazonMermaDTO razonMermaDTO;

    @NotNull
    private MermaDTO mermaDTO;

    public MermaInsumoDTO(){
        setTipoMovimiento("MERMA");
    }

}
