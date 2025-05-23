package com.adminease.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.adminease.backend.api.dto.request.SalidaInsumoRequest;
import com.adminease.backend.api.dto.response.SalidaInsumoResponse;
import com.adminease.backend.model.SalidaInsumo;

@Mapper(componentModel = "spring", uses = EntityResolver.class)
public interface SalidaInsumoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "salida", ignore = true)
    @Mapping(target = "insumo", source = "insumoId", qualifiedByName = "idToInsumo")
    SalidaInsumo toEntity(SalidaInsumoRequest request);

    @Mapping(target = "insumoId", source = "insumo.id")
    @Mapping(target = "insumoNombre", source = "insumo.nombre")
    SalidaInsumoResponse toResponse(SalidaInsumo salidaInsumo);
}

