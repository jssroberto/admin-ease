package com.adminease.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.adminease.backend.api.dto.request.CompraInsumoRequest;
import com.adminease.backend.api.dto.response.CompraInsumoResponse;
import com.adminease.backend.model.CompraInsumo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompraInsumoMapper {

    @Mapping(target = "insumo", ignore = true)
    @Mapping(target = "compra", ignore = true)
    CompraInsumo toEntity(CompraInsumoRequest compraInsumo);

    @Mapping(target = "insumo", ignore = true)
    CompraInsumoResponse toResponse(CompraInsumo compraInsumo);
}
