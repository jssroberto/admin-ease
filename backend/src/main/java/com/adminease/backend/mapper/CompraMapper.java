package com.adminease.backend.mapper;

import com.adminease.backend.dtos.CompraInsumoDTO;
import com.adminease.backend.dtos.MovimientoInsumoDTO;
import com.adminease.backend.dtos.CompraDTO;
import com.adminease.backend.model.CompraInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.adminease.backend.model.Compra;

@Mapper(componentModel = "spring")
public interface CompraMapper {
    CompraDTO toDTO(Compra compra);
    Compra toEntity(CompraDTO compraDTO);
}
