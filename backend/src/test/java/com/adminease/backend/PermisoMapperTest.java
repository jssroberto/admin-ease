package com.adminease.backend;

import com.adminease.backend.dtos.PermisoDTO;
import com.adminease.backend.model.Permiso;
import com.adminease.backend.model.Rol;
import com.adminease.backend.mapper.PermisoMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class PermisoMapperTest {

    PermisoMapper permisoMapper = Mappers.getMapper(PermisoMapper.class);

    @Test
    void shouldMapToDTO() {
        // Arrange
        Rol rol1 = new Rol(1L, "Admin", null, null);
        Rol rol2 = new Rol(2L, "Manager", null, null);
        Permiso permiso = new Permiso(1L, "Permiso1", List.of(rol1, rol2));

        // Act
        PermisoDTO permisoDTO = permisoMapper.toDTO(permiso);

        // Assert
        assertEquals(permiso.getId(), permisoDTO.getId());
        assertEquals(permiso.getNombre(), permisoDTO.getNombre());
        assertEquals(permiso.getRoles().size(), permisoDTO.getRolIds().size());
        assertEquals(rol1.getId(), permisoDTO.getRolIds().get(0));
        assertEquals(rol2.getId(), permisoDTO.getRolIds().get(1));
    }

    @Test
    void shouldMapToEntity() {
        // Arrange
        PermisoDTO permisoDTO = new PermisoDTO(1L, "Permiso1", List.of(1L, 2L));

        // Act
        Permiso permiso = permisoMapper.toEntity(permisoDTO);

        // Assert
        assertEquals(permisoDTO.getId(), permiso.getId());
        assertEquals(permisoDTO.getNombre(), permiso.getNombre());
        assertEquals(permisoDTO.getRolIds().size(), permiso.getRoles().size());
        assertEquals(permisoDTO.getRolIds().get(0), permiso.getRoles().get(0).getId());
        assertEquals(permisoDTO.getRolIds().get(1), permiso.getRoles().get(1).getId());
    }
}
