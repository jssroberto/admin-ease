//package com.adminease.backend.mapper;
//
//import com.adminease.backend.dto.CompraDTO;
//import org.mapstruct.Mapper;
//import com.adminease.backend.model.Compra;
//
//@Mapper(componentModel = "spring")
//public interface CompraMapper {
//    CompraDTO toDTO(Compra compra);
//    Compra toEntity(CompraDTO compraDTO);
//}
package com.adminease.backend.mapper;

import com.adminease.backend.dto.CompraDTO;
import com.adminease.backend.model.Compra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ProveedorMapper.class, UsuarioMapper.class, CompraInsumoMapper.class})
public interface CompraMapper {

    CompraMapper INSTANCE = Mappers.getMapper(CompraMapper.class);

    @Mapping(source = "proveedor", target = "proveedorDTO")
    @Mapping(source = "usuario", target = "usuarioDTO")
    @Mapping(source = "compraInsumos", target = "compraInsumoDTOs")
    CompraDTO toDTO(Compra compra);

    @Mapping(source = "proveedorDTO", target = "proveedor")
    @Mapping(source = "usuarioDTO", target = "usuario")
    @Mapping(source = "compraInsumoDTOs", target = "compraInsumos")
    Compra toEntity(CompraDTO compraDTO);
}
