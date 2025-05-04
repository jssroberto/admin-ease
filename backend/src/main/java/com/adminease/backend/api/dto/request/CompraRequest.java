package com.adminease.backend.api.dto.request;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

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
