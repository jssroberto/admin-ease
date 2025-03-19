package com.adminease.backend.service;

import com.adminease.backend.api.dto.request.SalidaRequest;
import com.adminease.backend.api.dto.response.SalidaInsumoResponse;
import com.adminease.backend.api.dto.response.SalidaResponse;
import com.adminease.backend.dto.SalidaDTO;
import com.adminease.backend.mapper.SalidaInsumoMapper;
import com.adminease.backend.mapper.SalidaMapper;
import com.adminease.backend.model.*;
import com.adminease.backend.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalidaService {

    private final SalidaRepository salidaRepository;
    private final SalidaInsumoService salidaInsumoService;
    private final SalidaMapper salidaMapper;

    @Transactional
    public SalidaResponse createSalida(SalidaRequest request) {
        // Convert request to entity
        Salida salida = salidaMapper.toEntity(request);
        salida.setFecha(LocalDateTime.now());
        // Create and map SalidaInsumos

        // Persist Salida
        salida = salidaRepository.save(salida);

        return salidaMapper.toResponse(salida);
    }

}
