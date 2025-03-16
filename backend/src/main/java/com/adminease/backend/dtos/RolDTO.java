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
public class RolDTO {

    private Long id;
    private String name;
    private List<Long> usuarioIds;
    private List<Long> permisoIds;

}
