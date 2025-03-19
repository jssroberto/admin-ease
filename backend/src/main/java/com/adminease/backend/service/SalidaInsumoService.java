package com.adminease.backend.service;

import com.adminease.backend.api.dto.request.SalidaInsumoRequest;
import com.adminease.backend.api.dto.response.SalidaInsumoResponse;
import com.adminease.backend.mapper.SalidaInsumoMapper;
import com.adminease.backend.model.Insumo;
import com.adminease.backend.model.Salida;
import com.adminease.backend.model.SalidaInsumo;
import com.adminease.backend.repository.InsumoRepository;
import com.adminease.backend.repository.SalidaInsumoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalidaInsumoService {

    private final SalidaInsumoRepository salidaInsumoRepository;
    private final InsumoService insumoService;

    private final InsumoRepository insumoRepository;

    private final SalidaInsumoMapper salidaInsumoMapper;

    @Transactional
    public List<SalidaInsumoResponse> createSalidaInsumos(Salida salida, List<SalidaInsumoRequest> requests) {
        List<SalidaInsumo> salidaInsumos = new ArrayList<>();

        for (SalidaInsumoRequest request : requests) {
            Optional<Insumo> insumo = insumoRepository.findById(request.getInsumoId());
            if (insumo.isEmpty()) {
                throw new EntityNotFoundException("Insumo with id " + request.getInsumoId() + " not found");
            }

            SalidaInsumo salidaInsumo = salidaInsumoMapper.toEntity(request);
            salidaInsumo.setSalida(salida);
            salidaInsumos.add(salidaInsumo);
        }

        salidaInsumos = salidaInsumoRepository.saveAll(salidaInsumos);

        for (SalidaInsumo salidaInsumo : salidaInsumos) {
            insumoService.decreaseStock(
                    salidaInsumo.getInsumo().getId(),
                    salidaInsumo.getCantidad());
        }

        List<SalidaInsumoResponse> responses = new ArrayList<>();

        for (SalidaInsumo salidaInsumo : salidaInsumos) {
            responses.add(salidaInsumoMapper.toResponse(salidaInsumo));
        }

        return responses;

    }

}
