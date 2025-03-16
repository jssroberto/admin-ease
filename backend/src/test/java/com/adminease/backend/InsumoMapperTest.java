package com.adminease.backend;

import com.adminease.backend.dtos.InsumoDTO;
import com.adminease.backend.mapper.InsumoMapper;
import com.adminease.backend.model.Insumo;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsumoMapperTest {

    InsumoMapper insumoMapper = Mappers.getMapper(InsumoMapper.class);

    @Test
    void shouldMapToDTO() {
        // Arrange
        Insumo insumo = new Insumo(1L, "001", "Insumo1", "kg", 100, "Categoria1", null, null);

        // Act
        InsumoDTO insumoDTO = insumoMapper.toDTO(insumo);

        // Assert
        assertEquals(insumo.getInsumoId(), insumoDTO.getInsumoId());
        assertEquals(insumo.getCodigo(), insumoDTO.getCodigo());
        assertEquals(insumo.getNombre(), insumoDTO.getNombre());
        assertEquals(insumo.getUnidadMedida(), insumoDTO.getUnidadMedida());
        assertEquals(insumo.getStock(), insumoDTO.getStock());
        assertEquals(insumo.getCategoria(), insumoDTO.getCategoria());
    }

    @Test
    void shouldMapToEntity() {
        // Arrange
        InsumoDTO insumoDTO = new InsumoDTO(1L, "001", "Insumo1", "kg", 100, "Categoria1", null, null);

        // Act
        Insumo insumo = insumoMapper.toEntity(insumoDTO);

        // Assert
        assertEquals(insumoDTO.getInsumoId(), insumo.getInsumoId());
        assertEquals(insumoDTO.getCodigo(), insumo.getCodigo());
        assertEquals(insumoDTO.getNombre(), insumo.getNombre());
        assertEquals(insumoDTO.getUnidadMedida(), insumo.getUnidadMedida());
        assertEquals(insumoDTO.getStock(), insumo.getStock());
        assertEquals(insumoDTO.getCategoria(), insumo.getCategoria());
    }
}
