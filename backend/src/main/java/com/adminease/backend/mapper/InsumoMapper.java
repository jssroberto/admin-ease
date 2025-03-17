package com.adminease.backend.mapper;

import com.adminease.backend.dto.InsumoDTO;
import com.adminease.backend.model.Insumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsumoMapper {

    InsumoMapper INSTANCE = Mappers.getMapper(InsumoMapper.class);

    @Mapping(source = "productosInsumo", target = "productosInsumo")
    @Mapping(source = "movimientosInsumo", target = "movimientosInsumo")
    InsumoDTO toDTO(Insumo insumo);

    @Mapping(source = "productosInsumo", target = "productosInsumo")
    @Mapping(source = "movimientosInsumo", target = "movimientosInsumo")
    Insumo toEntity(InsumoDTO insumoDTO);

    List<InsumoDTO> toDTOList(List<Insumo> insumos);

    List<Insumo> toEntityList(List<InsumoDTO> insumoDTOs);
}
