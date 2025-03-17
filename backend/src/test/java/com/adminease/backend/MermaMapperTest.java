package com.adminease.backend;

import com.adminease.backend.dto.MermaDTO;
import com.adminease.backend.model.Merma;
import com.adminease.backend.mapper.MermaMapper;
import com.adminease.backend.model.Usuario;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MermaMapperTest {

    MermaMapper mermaMapper = Mappers.getMapper(MermaMapper.class);

    @Test
    void shouldMapToDTO() {
        // Arrange
        Merma merma = new Merma(1L, new Usuario(), null);

        // Act
        MermaDTO mermaDTO = mermaMapper.toDTO(merma);

        // Assert
        assertEquals(merma.getMermaId(), mermaDTO.getMermaId());
        assertEquals(merma.getUsuario().getId(), mermaDTO.getUsuarioId());
    }

    @Test
    void shouldMapToEntity() {
        // Arrange
        MermaDTO mermaDTO = new MermaDTO(1L, 1L, null);

        // Act
        Merma merma = mermaMapper.toEntity(mermaDTO);

        // Assert
        assertEquals(mermaDTO.getMermaId(), merma.getMermaId());
        assertEquals(mermaDTO.getUsuarioId(), merma.getUsuario().getId());
    }
}
