package com.adminease.backend.service;

import com.adminease.backend.api.dto.response.InsumoResponse;
import com.adminease.backend.mapper.InsumoMapper;
import com.adminease.backend.model.Insumo;
import com.adminease.backend.repository.InsumoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InsumoService {

    private final InsumoRepository insumoRepository;

    private final InsumoMapper insumoMapper;

    public InsumoResponse findById(Long id) {

        Optional<Insumo> insumo = insumoRepository.findById(id);

        if (insumo.isEmpty()) {
            throw new EntityNotFoundException("Insumo not found");
        }

        return insumoMapper.toResponse(insumo.get());
    }

}
