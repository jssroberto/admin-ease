package com.adminease.backend.api.dto.request;

import java.time.ZonedDateTime;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompraRequest {



    @NotNull
    private ZonedDateTime fecha;

    @NotNull
    private Long proveedorId;

    @NotNull
    private Long usuarioId;

    @NotNull
    private List<CompraInsumoRequest> compraInsumos;
}
