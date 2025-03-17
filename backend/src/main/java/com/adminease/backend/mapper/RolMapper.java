package com.adminease.backend.mapper;

import com.adminease.backend.dto.RolDTO;
import com.adminease.backend.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RolMapper {

    RolMapper INSTANCE = Mappers.getMapper(RolMapper.class);

    RolDTO toDTO(Rol rol);

    Rol toEntity(RolDTO rolDTO);
}
