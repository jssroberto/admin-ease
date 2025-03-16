package com.adminease.backend;

import com.adminease.backend.dtos.SalidaDTO;
import com.adminease.backend.model.Salida;
import com.adminease.backend.mapper.SalidaMapper;
import com.adminease.backend.model.SalidaInsumo;
import com.adminease.backend.model.Usuario;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalidaMapperTest {

    SalidaMapper salidaMapper = Mappers.getMapper(SalidaMapper.class);

    @Test
    void shouldMapToDTO() {
        // Arrange
        Salida salida = new Salida(1L, "Area1", new Usuario(), List.of(new SalidaInsumo(), new SalidaInsumo()));

        // Act
        SalidaDTO salidaDTO = salidaMapper.toDTO(salida);

        // Assert
        assertEquals(salida.getSalidaId(), salidaDTO.getSalidaId());
        assertEquals(salida.getArea(), salidaDTO.getArea());
//        assertEquals(salida.getUsuarioId(), salidaDTO.getUsuarioId());
//        assertEquals(salida.getSalidaInsumoIds(), salidaDTO.getSalidaInsumoIds());
    }

    @Test
    void shouldMapToEntity() {
        // Arrange
        SalidaDTO salidaDTO = new SalidaDTO(1L, "Area1", 1L, List.of(1L, 2L));

        // Act
        Salida salida = salidaMapper.toEntity(salidaDTO);

        // Assert
        assertEquals(salidaDTO.getSalidaId(), salida.getSalidaId());
        assertEquals(salidaDTO.getArea(), salida.getArea());
//        assertEquals(salidaDTO.getUsuarioId(), salida.getUsuarioId());
//        assertEquals(salidaDTO.getSalidaInsumoIds(), salida.getSalidaInsumoIds());
    }
}
