package com.adminease.backend.mapper;

import com.adminease.backend.api.dto.request.UnidadMedidaRequest;
import com.adminease.backend.api.dto.response.UnidadMedidaResponse;
import com.adminease.backend.model.UnidadMedida;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UnidadMedidaMapper {

    UnidadMedidaResponse toResponse(UnidadMedida unidadMedida);

    UnidadMedida toEntity(UnidadMedidaRequest unidadMedidaRequest);
}

