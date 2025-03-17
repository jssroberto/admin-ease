package com.adminease.backend;

import com.adminease.backend.dto.UsuarioDTO;
import com.adminease.backend.model.Rol;
import com.adminease.backend.model.Usuario;
import com.adminease.backend.mapper.UsuarioMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class UsuarioMapperTest {

    UsuarioMapper usuarioMapper = Mappers.getMapper(UsuarioMapper.class);

    @Test
    void shouldMapToDTO() {
        // Arrange
        Usuario usuario = new Usuario(1L, "Juan", "password123", new Rol());

        // Act
        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario);

        // Assert
        assertEquals(usuario.getId(), usuarioDTO.getId());
        assertEquals(usuario.getNombre(), usuarioDTO.getNombre());
        assertEquals(usuario.getContrasena(), usuarioDTO.getContrasena());
        assertEquals(usuario.getRol().getId(), usuarioDTO.getRolId());
    }

    @Test
    void shouldMapToEntity() {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO(1L, "Juan", "password123", 1L);

        // Act
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);

        // Assert
        assertEquals(usuarioDTO.getId(), usuario.getId());
        assertEquals(usuarioDTO.getNombre(), usuario.getNombre());
        assertEquals(usuarioDTO.getContrasena(), usuario.getContrasena());
        assertEquals(usuarioDTO.getRolId(), usuario.getRol().getId());
    }
}
