package com.adminease.backend.mapper;

import com.adminease.backend.dto.SalidaInsumoDTO;
import com.adminease.backend.model.SalidaInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SalidaInsumoMapper {

    SalidaInsumoMapper INSTANCE = Mappers.getMapper(SalidaInsumoMapper.class);

    SalidaInsumoDTO toDTO(SalidaInsumo salidaInsumo);

    SalidaInsumo toEntity(SalidaInsumoDTO salidaInsumoDTO);
}
