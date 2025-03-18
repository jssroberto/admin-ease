package com.adminease.backend.mapper;

import com.adminease.backend.dto.CategoriaProductoDTO;
import com.adminease.backend.model.CategoriaProducto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoriaProductoMapper {

    CategoriaProductoMapper INSTANCE = Mappers.getMapper(CategoriaProductoMapper.class);

    CategoriaProductoDTO toDTO(CategoriaProducto categoriaProducto);

    CategoriaProducto toEntity(CategoriaProductoDTO categoriaProductoDTO);
}
