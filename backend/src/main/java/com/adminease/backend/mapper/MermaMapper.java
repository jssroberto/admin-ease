package com.adminease.backend.mapper;

import com.adminease.backend.dtos.MermaDTO;
import com.adminease.backend.model.Merma;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MermaMapper {

    MermaMapper INSTANCE = Mappers.getMapper(MermaMapper.class);

    MermaDTO toDTO(Merma merma);

    Merma toEntity(MermaDTO mermaDTO);
}
