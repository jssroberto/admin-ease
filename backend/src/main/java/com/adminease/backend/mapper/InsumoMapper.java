package com.adminease.backend.mapper;

import com.adminease.backend.api.dto.request.InsumoRequest;
import com.adminease.backend.api.dto.response.InsumoResponse;
import com.adminease.backend.model.Insumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InsumoMapper {

    @Mapping(target = "unidadMedida", ignore = true)
    @Mapping(target = "categoriaInsumo", ignore = true)
    @Mapping(target = "insumosProductos", ignore = true)
    @Mapping(target = "movimientoInsumos", ignore = true)
    Insumo toEntity(InsumoRequest request);

    @Mapping(target = "unidadMedidaId", source = "unidadMedida.id")
    @Mapping(target = "categoriaInsumoId", source = "categoriaInsumo.id")
    InsumoResponse toResponse(Insumo insumo);
}
