package com.adminease.backend.mapper;

import com.adminease.backend.api.dto.request.RolRequest;
import com.adminease.backend.api.dto.response.RolResponse;
import com.adminease.backend.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {PermisoMapper.class})
public interface RolMapper {

    @Mapping(target = "permisos", ignore = true)
    @Mapping(target = "usuarios", ignore = true)
    Rol toEntity(RolRequest rolRequest);

    RolResponse toResponse(Rol rol);

}
