package com.adminease.backend.mapper;

import com.adminease.backend.dtos.UsuarioDTO;
import com.adminease.backend.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    // Mapeo de Usuario a UsuarioDTO
    @Mapping(source = "rol.id", target = "rolId")  // Mapea el id del rol a rolId en el DTO
    UsuarioDTO toDTO(Usuario usuario);

    // Mapeo de UsuarioDTO a Usuario
    @Mapping(source = "rolId", target = "rol.id")  // Mapea rolId a id del rol en la entidad
    Usuario toEntity(UsuarioDTO usuarioDTO);
}
