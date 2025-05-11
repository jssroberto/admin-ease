package com.adminease.backend.mapper;

import com.adminease.backend.api.dto.request.SalidaRequest;
import com.adminease.backend.api.dto.response.SalidaResponse;
import com.adminease.backend.model.Salida;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Mapper(componentModel = "spring", uses = {SalidaInsumoMapper.class, EntityResolver.class})
public interface SalidaMapper {


    @Mapping(target = "fecha", ignore = true) 
    @Mapping(target = "area", source = "areaId", qualifiedByName = "idToArea")
    @Mapping(target = "usuario", source = "usuarioId", qualifiedByName = "idToUsuario")
    @Mapping(target = "salidaInsumos", source = "salidaInsumoRequests")
    Salida toEntity(SalidaRequest request);

    @Mapping(target = "areaId", source = "area.id")
    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "salidaInsumos", source = "salidaInsumos")
    SalidaResponse toResponse(Salida salida);

    @AfterMapping
    default void setCurrentTimestamp(@MappingTarget Salida salida) {
        salida.setFecha(ZonedDateTime.now(ZoneOffset.UTC));
    }
}
