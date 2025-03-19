package com.adminease.backend.service;

import com.adminease.backend.api.dto.request.SalidaRequest;
import com.adminease.backend.api.dto.response.SalidaInsumoResponse;
import com.adminease.backend.api.dto.response.SalidaResponse;
import com.adminease.backend.mapper.SalidaMapper;
import com.adminease.backend.model.Salida;
import com.adminease.backend.repository.SalidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalidaService {

    private final SalidaRepository salidaRepository;
    private final SalidaInsumoService salidaInsumoService;
    private final InsumoService insumoService;
    private final SalidaMapper salidaMapper;

    @Transactional
    public SalidaResponse createSalida(SalidaRequest request) {
        // Convert request to entity
        Salida salida = salidaMapper.toEntity(request);
        salida.setFecha(ZonedDateTime.now(ZoneOffset.UTC));
        // Persist Salida
        salida = salidaRepository.save(salida);

        List<SalidaInsumoResponse> responses = salidaInsumoService.createSalidaInsumos(
                salida,
                request.getSalidaInsumoRequests());

        SalidaResponse salidaResponse = salidaMapper.toResponse(salida);
        salidaResponse.setSalidaInsumos(responses);




        return salidaResponse;
    }

}
