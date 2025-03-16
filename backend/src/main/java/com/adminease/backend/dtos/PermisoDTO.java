package com.adminease.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PermisoDTO {

    private Long id;
    private String nombre;
    private List<Long> rolIds;
}
