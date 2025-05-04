package com.adminease.backend.mapper;

import com.adminease.backend.api.dto.request.ProveedorRequest;
import com.adminease.backend.api.dto.response.ProveedorResponse;
import com.adminease.backend.model.Proveedor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProveedorMapper {

    Proveedor toEntity(ProveedorRequest proveedorRequest);

    ProveedorResponse toResponse(Proveedor proveedor);
}
