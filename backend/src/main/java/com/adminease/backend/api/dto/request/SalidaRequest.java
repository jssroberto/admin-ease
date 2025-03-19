package com.adminease.backend.api.dto.request;

import com.adminease.backend.service.dto.SalidaInsumoDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty
    private List<SalidaInsumoRequest> salidaInsumoRequests;

}