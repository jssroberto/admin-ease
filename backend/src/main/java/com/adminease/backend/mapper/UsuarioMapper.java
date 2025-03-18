package com.adminease.backend.mapper;

import com.adminease.backend.dto.UsuarioDTO;
import com.adminease.backend.dto.UsuarioRequestDTO;
import com.adminease.backend.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {RolMapper.class})  // Usamos el mapper de Rol para convertir entre Rol y RolDTO
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(source = "rol", target = "rolDTO")  // Mapeo de la relación con Rol
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(source = "rolDTO", target = "rol")  // Mapeo de la relación con Rol
    Usuario toEntity(UsuarioDTO usuarioDTO);

    @Mapping(source = "rolDTO", target = "rol")  // Aquí mapeamos el DTO de Rol a la entidad Rol
    Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO);

    @Mapping(source = "rol", target = "rolDTO")  // Aquí mapeamos el DTO de Rol a la entidad Rol
    UsuarioRequestDTO toEntity(Usuario usuario);
}
