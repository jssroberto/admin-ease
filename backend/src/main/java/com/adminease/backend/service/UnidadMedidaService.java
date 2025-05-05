package com.adminease.backend.service;

import com.adminease.backend.api.dto.response.UnidadMedidaResponse;
import com.adminease.backend.mapper.UnidadMedidaMapper;
import com.adminease.backend.model.UnidadMedida;
import com.adminease.backend.repository.UnidadMedidaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnidadMedidaService {

    private final UnidadMedidaRepository unidadMedidaRepository;

    private final UnidadMedidaMapper unidadMedidaMapper;

    public UnidadMedidaResponse getAreaById(Long id) {

        Optional<UnidadMedida> unidadMedida = unidadMedidaRepository.findById(id);

        if (unidadMedida.isEmpty()) {
            throw new RuntimeException("Area not found with ID: " + id);
        }

        return new UnidadMedidaResponse(
            unidadMedida.get().getId(),
            unidadMedida.get().getNombre());
    }

    public List<UnidadMedidaResponse> getAllUnidadesMeedida() {
        List<UnidadMedida> unidadesDeMedida = unidadMedidaRepository.findAll();

        if (unidadesDeMedida.isEmpty()) {
            throw new RuntimeException("No unidades de medida found");
        }

        return unidadesDeMedida.stream()
                .map(unidadMedidaMapper::toResponse)
                .toList();
    }

}
