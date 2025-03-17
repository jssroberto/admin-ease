package com.adminease.backend.mapper;

import com.adminease.backend.dto.MermaInsumoDTO;
import com.adminease.backend.model.MermaInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MermaInsumoMapper {

    MermaInsumoMapper INSTANCE = Mappers.getMapper(MermaInsumoMapper.class);

    MermaInsumoDTO toDTO(MermaInsumo mermaInsumo);

    MermaInsumo toEntity(MermaInsumoDTO mermaInsumoDTO);
}
