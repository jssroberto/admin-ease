package com.adminease.backend.mapper;

import com.adminease.backend.api.dto.request.SalidaInsumoRequest;
import com.adminease.backend.api.dto.response.SalidaInsumoResponse;
import com.adminease.backend.model.SalidaInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EntityResolver.class)
public interface SalidaInsumoMapper {

    @Mapping(target = "salida", ignore = true)
    @Mapping(target = "insumo", source = "insumoId", qualifiedByName = "idToInsumo")
    SalidaInsumo toEntity(SalidaInsumoRequest request);

    @Mapping(target = "insumoId", source = "insumo.id")
    SalidaInsumoResponse toResponse(SalidaInsumo salidaInsumo);
}

