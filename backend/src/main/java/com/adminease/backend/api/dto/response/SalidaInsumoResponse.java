package com.adminease.backend.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalidaInsumoResponse {

    private Long id;

    private Double cantidad;

    private Long insumoId;

    private Long salidaId;

}
