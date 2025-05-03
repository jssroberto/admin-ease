package com.adminease.backend.mapper;

import com.adminease.backend.api.dto.request.CompraRequest;
import com.adminease.backend.api.dto.response.CompraResponse;
import com.adminease.backend.model.Compra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompraMapper {

    @Mapping(target = "proveedor", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "compraInsumos", ignore = true)
    Compra toEntity(CompraRequest compraRequest);

    @Mapping(target = "proveedorNombre", source = "proveedor")
    @Mapping(target = "usuarioNombre", source = "usuario")
    @Mapping(target = "compraInsumos", source = "compraInsumos")
    CompraResponse toResponse(Compra compra);
}
