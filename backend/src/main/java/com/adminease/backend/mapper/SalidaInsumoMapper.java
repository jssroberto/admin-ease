package com.adminease.backend.mapper;

import com.adminease.backend.api.dto.request.SalidaInsumoRequest;
import com.adminease.backend.api.dto.response.SalidaInsumoResponse;
import com.adminease.backend.model.Insumo;
import com.adminease.backend.service.dto.SalidaInsumoDto;
import com.adminease.backend.model.SalidaInsumo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityResolver.class)
public interface SalidaInsumoMapper {

    // Convert SalidaInsumoRequest to SalidaInsumo
    @Mapping(target = "salida", ignore = true) // Set later in Salida mapping
    @Mapping(target = "insumo", source = "insumoId", qualifiedByName = "idToInsumo")
    SalidaInsumo toEntity(SalidaInsumoRequest request);

    // Convert SalidaInsumo to SalidaInsumoResponse
    @Mapping(target = "insumoId", source = "insumo.id")
    SalidaInsumoResponse toResponse(SalidaInsumo salidaInsumo);
}

