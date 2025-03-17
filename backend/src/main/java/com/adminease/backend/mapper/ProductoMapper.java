package com.adminease.backend.mapper;

import com.adminease.backend.dto.ProductoDTO;
import com.adminease.backend.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    ProductoDTO toDTO(Producto producto);

    Producto toEntity(ProductoDTO productoDTO);
}
