package com.adminease.backend.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalidaInsumoRequest {

    @NotNull
    @Positive
    private Double cantidad;

    @NotNull
    private Long insumoId;

}