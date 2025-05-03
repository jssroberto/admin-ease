package com.adminease.backend.api.dto.response;

import com.adminease.backend.dto.ProveedorDTO;
import com.adminease.backend.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class CompraResponse {

    private Long id;

    private Double total;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private ZonedDateTime fecha;

    private ProveedorDTO proveedorNombre;

    private UsuarioDTO usuarioNombre;

    private List<CompraInsumoResponse> compraInsumos;
}
