package com.adminease.backend.mapper;

import com.adminease.backend.dto.CompraDTO;
import org.mapstruct.Mapper;
import com.adminease.backend.model.Compra;

@Mapper(componentModel = "spring")
public interface CompraMapper {
    CompraDTO toDTO(Compra compra);
    Compra toEntity(CompraDTO compraDTO);
}
