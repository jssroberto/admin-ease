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
public class SalidaDTO {

    private Long salidaId;
    private String area;
    private Long usuarioId;
    private List<Long> salidaInsumoIds;

}
