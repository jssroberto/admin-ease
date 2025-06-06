package com.adminease.backend.api.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RolResponse {

    private Long id;

    private String nombre;

    private List<PermisoResponse> permisos;

}
