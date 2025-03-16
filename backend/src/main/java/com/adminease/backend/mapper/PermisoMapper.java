package com.adminease.backend.mapper;

import com.adminease.backend.dtos.PermisoDTO;
import com.adminease.backend.model.Permiso;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PermisoMapper {

    PermisoMapper INSTANCE = Mappers.getMapper(PermisoMapper.class);

    PermisoDTO toDTO(Permiso permiso);

    Permiso toEntity(PermisoDTO permisoDTO);
}
