package com.adminease.backend.mapper;

import com.adminease.backend.dto.SalidaDTO;
import com.adminease.backend.model.Salida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SalidaMapper {

    SalidaMapper INSTANCE = Mappers.getMapper(SalidaMapper.class);

    SalidaDTO toDTO(Salida salida);

    Salida toEntity(SalidaDTO salidaDTO);
}
