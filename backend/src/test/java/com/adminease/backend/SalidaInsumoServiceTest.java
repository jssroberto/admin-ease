/* package com.adminease.backend.service;

import com.adminease.backend.api.dto.response.InsumoResponse;
import com.adminease.backend.mapper.InsumoMapper;
import com.adminease.backend.model.Insumo;
import com.adminease.backend.repository.InsumoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InsumoServiceTest {

    @Mock
    private InsumoRepository insumoRepository;

    @Mock
    private InsumoMapper insumoMapper;

    @InjectMocks
    private InsumoService insumoService;

    @Test
    void findById_shouldReturnInsumoResponse_whenIdExists() {
        Insumo insumo = new Insumo();
        insumo.setId(1L);
        InsumoResponse response = new InsumoResponse();

        when(insumoRepository.findById(1L)).thenReturn(Optional.of(insumo));
        when(insumoMapper.toResponse(insumo)).thenReturn(response);

        InsumoResponse result = insumoService.findById(1L);

        assertEquals(response, result);
    }

    @Test
    void findById_shouldThrowException_whenIdDoesNotExist() {
        when(insumoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> insumoService.findById(1L));
    }

    @Test
    void increaseStock_shouldIncreaseStock_whenValid() {
        Insumo insumo = new Insumo();
        insumo.setId(1L);
        insumo.setStock(10.0);
        InsumoResponse response = new InsumoResponse();

        when(insumoRepository.findById(1L)).thenReturn(Optional.of(insumo));
        when(insumoRepository.save(insumo)).thenReturn(insumo);
        when(insumoMapper.toResponse(insumo)).thenReturn(response);

        InsumoResponse result = insumoService.increaseStock(1L, 5.0);

        assertEquals(response, result);
        assertEquals(15.0, insumo.getStock());
    }

    @Test
    void increaseStock_shouldThrowException_whenQuantityIsZero() {
        assertThrows(IllegalArgumentException.class, () -> insumoService.increaseStock(1L, 0.0));
    }

    @Test
    void increaseStock_shouldThrowException_whenIdNotFound() {
        when(insumoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> insumoService.increaseStock(1L, 5.0));
    }

    @Test
    void decreaseStock_shouldDecreaseStock_whenValid() {
        Insumo insumo = new Insumo();
        insumo.setId(1L);
        insumo.setStock(10.0);
        InsumoResponse response = new InsumoResponse();

        when(insumoRepository.findById(1L)).thenReturn(Optional.of(insumo));
        when(insumoRepository.save(insumo)).thenReturn(insumo);
        when(insumoMapper.toResponse(insumo)).thenReturn(response);

        InsumoResponse result = insumoService.decreaseStock(1L, 4.0);

        assertEquals(response, result);
        assertEquals(6.0, insumo.getStock());
    }

    @Test
    void decreaseStock_shouldThrowException_whenQuantityIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> insumoService.decreaseStock(1L, -1.0));
    }

    @Test
    void decreaseStock_shouldThrowException_whenIdNotFound() {
        when(insumoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> insumoService.decreaseStock(1L, 2.0));
    }

    @Test
    void decreaseStock_shouldThrowException_whenInsufficientStock() {
        Insumo insumo = new Insumo();
        insumo.setStock(3.0);

        when(insumoRepository.findById(1L)).thenReturn(Optional.of(insumo));

        assertThrows(IllegalArgumentException.class, () -> insumoService.decreaseStock(1L, 5.0));
    }

    @Test
    void findByNameContainingIgnoreCase_shouldReturnList_whenMatchesExist() {
        Insumo insumo1 = new Insumo();
        Insumo insumo2 = new Insumo();
        InsumoResponse response1 = new InsumoResponse();
        InsumoResponse response2 = new InsumoResponse();

        List<Insumo> insumos = List.of(insumo1, insumo2);
        when(insumoRepository.findByNombreContainingIgnoreCase("agua")).thenReturn(insumos);
        when(insumoMapper.toResponse(insumo1)).thenReturn(response1);
        when(insumoMapper.toResponse(insumo2)).thenReturn(response2);

        List<InsumoResponse> responses = insumoService.findByNameContainingIgnoreCase("agua");

        assertEquals(2, responses.size());
        assertTrue(responses.contains(response1));
        assertTrue(responses.contains(response2));
    }

    @Test
    void findByNameContainingIgnoreCase_shouldThrowException_whenNoMatches() {
        when(insumoRepository.findByNombreContainingIgnoreCase("xyz")).thenReturn(List.of());

        assertThrows(EntityNotFoundException.class, () -> insumoService.findByNameContainingIgnoreCase("xyz"));
    }

    // Pruebas adicionales para robustez
    @Test
    void increaseStock_shouldSaveInsumoWithUpdatedStock() {
        Insumo insumo = new Insumo();
        insumo.setId(1L);
        insumo.setStock(5.0);
        InsumoResponse response = new InsumoResponse();

        when(insumoRepository.findById(1L)).thenReturn(Optional.of(insumo));
        when(insumoRepository.save(any())).thenReturn(insumo);
        when(insumoMapper.toResponse(insumo)).thenReturn(response);

        insumoService.increaseStock(1L, 2.0);

        verify(insumoRepository).save(argThat(i -> i.getStock().equals(7.0)));
    }

    @Test
    void decreaseStock_shouldSaveInsumoWithUpdatedStock() {
        Insumo insumo = new Insumo();
        insumo.setId(1L);
        insumo.setStock(10.0);
        InsumoResponse response = new InsumoResponse();

        when(insumoRepository.findById(1L)).thenReturn(Optional.of(insumo));
        when(insumoRepository.save(any())).thenReturn(insumo);
        when(insumoMapper.toResponse(insumo)).thenReturn(response);

        insumoService.decreaseStock(1L, 3.0);

        verify(insumoRepository).save(argThat(i -> i.getStock().equals(7.0)));
    }

    @Test
    void findById_shouldCallMapperExactlyOnce() {
        Insumo insumo = new Insumo();
        insumo.setId(1L);
        InsumoResponse response = new InsumoResponse();

        when(insumoRepository.findById(1L)).thenReturn(Optional.of(insumo));
        when(insumoMapper.toResponse(insumo)).thenReturn(response);

        insumoService.findById(1L);

        verify(insumoMapper, times(1)).toResponse(insumo);
    }
}
 */