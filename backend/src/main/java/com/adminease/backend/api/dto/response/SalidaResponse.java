package com.adminease.backend.api.dto.response;

import java.time.ZonedDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalidaResponse {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private ZonedDateTime fecha;

    private Long areaId; // Changed from area

    private String areaNombre; // Changed from areaId

    private Long usuarioId; // Changed from usuario

    private String usuarioNombre; // Changed from usuarioId

    private List<SalidaInsumoResponse> salidaInsumos;

}
