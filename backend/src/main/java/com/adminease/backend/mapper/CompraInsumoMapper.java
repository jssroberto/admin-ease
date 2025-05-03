package com.adminease.backend.mapper;

import com.adminease.backend.api.dto.request.CompraInsumoRequest;
import com.adminease.backend.api.dto.response.CompraInsumoResponse;
import com.adminease.backend.model.CompraInsumo;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompraInsumoMapper {

    @Mapping(target = "insumo", ignore = true)
    @Mapping(target = "compra", ignore = true)
    CompraInsumo toEntity(CompraInsumoRequest compraInsumo);

    @Mapping(target = "insumo", ignore = true)
    CompraInsumoResponse toResponse(CompraInsumo compraInsumo);
}
