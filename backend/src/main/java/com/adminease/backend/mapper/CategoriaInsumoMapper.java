package com.adminease.backend.mapper;

import com.adminease.backend.api.dto.request.CategoriaInsumoRequest;
import com.adminease.backend.api.dto.response.CategoriaInsumoResponse;
import com.adminease.backend.model.CategoriaInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoriaInsumoMapper {

    CategoriaInsumoResponse toResponse(CategoriaInsumo categoriaInsumo);

    CategoriaInsumo toEntity(CategoriaInsumoRequest categoriaInsumoRequest);
}