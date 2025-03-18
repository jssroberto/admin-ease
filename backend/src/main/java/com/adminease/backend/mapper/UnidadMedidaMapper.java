package com.adminease.backend.mapper;

import com.adminease.backend.dto.UnidadMedidaDTO;
import com.adminease.backend.model.UnidadMedida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UnidadMedidaMapper {

    UnidadMedidaMapper INSTANCE = Mappers.getMapper(UnidadMedidaMapper.class);

    UnidadMedidaDTO toDTO(UnidadMedida unidadMedida);

    UnidadMedida toEntity(UnidadMedidaDTO unidadMedidaDTO);
}
