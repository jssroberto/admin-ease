package com.adminease.backend.mapper;

import com.adminease.backend.dtos.ProveedorDTO;
import com.adminease.backend.model.Proveedor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {

    ProveedorMapper INSTANCE = Mappers.getMapper(ProveedorMapper.class);

    ProveedorDTO toDTO(Proveedor proveedor);

    Proveedor toEntity(ProveedorDTO proveedorDTO);
}
