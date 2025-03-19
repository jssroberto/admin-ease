package com.adminease.backend.mapper;

import com.adminease.backend.api.dto.request.InsumoRequest;
import com.adminease.backend.api.dto.response.InsumoResponse;
import com.adminease.backend.dto.InsumoDTO;
import com.adminease.backend.model.Insumo;
import com.adminease.backend.repository.InsumoRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InsumoMapper {

    @Mapping(target = "unidadMedida", ignore = true)
    @Mapping(target = "categoriaInsumo", ignore = true)
    @Mapping(target = "insumosProductos", ignore = true)
    @Mapping(target = "movimientoInsumos", ignore = true)
    Insumo toEntity(InsumoRequest request);

    // Keep response mapping as original
    InsumoResponse toResponse(Insumo insumo);
}
