package com.adminease.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MermaDTO {

    private Long mermaId;
    private Long usuarioId;
    private List<MermaInsumoDTO> mermaInsumo;

}
