package com.adminease.backend.mapper;

import com.adminease.backend.api.dto.request.PermisoRequest;
import com.adminease.backend.api.dto.response.PermisoResponse;
import com.adminease.backend.model.Permiso;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermisoMapper {

    PermisoResponse toResponse(Permiso permiso);

    Permiso toEntity(PermisoRequest permisoRequest);
}