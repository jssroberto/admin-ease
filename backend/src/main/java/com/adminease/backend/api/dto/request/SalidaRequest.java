package com.adminease.backend.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalidaRequest {

    @NotNull
    private Long areaId;

    @NotNull
    private Long usuarioId;

    @NotNull
    @Size(min = 1)
    private List<SalidaInsumoRequest> salidaInsumoRequests;

}