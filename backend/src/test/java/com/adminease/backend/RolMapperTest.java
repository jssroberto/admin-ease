package com.adminease.backend;

import com.adminease.backend.dto.RolDTO;
import com.adminease.backend.model.Permiso;
import com.adminease.backend.model.Rol;
import com.adminease.backend.mapper.RolMapper;
import com.adminease.backend.model.Usuario;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RolMapperTest {

    RolMapper rolMapper = Mappers.getMapper(RolMapper.class);

    @Test
    void shouldMapToDTO() {
        // Arrange
        Rol rol = new Rol(1L, "Admin", List.of(new Usuario(), new Usuario()), List.of(new Permiso(), new Permiso()));

        // Act
        RolDTO rolDTO = rolMapper.toDTO(rol);

        // Assert
        assertEquals(rol.getId(), rolDTO.getId());
        assertEquals(rol.getName(), rolDTO.getName());
        assertEquals(rol.getId(), rolDTO.getUsuarioIds());
        assertEquals(rol.getPermisos(), rolDTO.getPermisoIds());
    }

    @Test
    void shouldMapToEntity() {
        // Arrange
        RolDTO rolDTO = new RolDTO(1L, "Admin", List.of(1L, 2L), List.of(1L, 2L));

        // Act
        Rol rol = rolMapper.toEntity(rolDTO);

        // Assert
        assertEquals(rolDTO.getId(), rol.getId());
        assertEquals(rolDTO.getName(), rol.getName());
        assertEquals(rolDTO.getUsuarioIds(), rol.getPermisos());
        assertEquals(rolDTO.getPermisoIds(), rol.getPermisos());
    }
}
