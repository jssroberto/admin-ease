package com.adminease.backend.service;

import com.adminease.backend.api.dto.response.InsumoResponse;
import com.adminease.backend.mapper.InsumoMapper;
import com.adminease.backend.model.Insumo;
import com.adminease.backend.repository.InsumoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

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

    @Transactional
    public InsumoResponse increaseStock(Long id, Double quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Insumo insumo = insumoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Insumo with id " + id + " not found"));

        insumo.setStock(insumo.getStock() + quantity);

        insumoRepository.save(insumo);

        return insumoMapper.toResponse(insumo);
    }

    @Transactional
    public InsumoResponse decreaseStock(Long id, Double quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity to subtract must be positive");
        }

        Insumo insumo = insumoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Insumo with id " + id + " not found"));

        if (insumo.getStock() < quantity) {
            throw new IllegalArgumentException("Insufficient stock to subtract");
        }

        insumo.setStock(insumo.getStock() - quantity);

        insumoRepository.save(insumo);

        return insumoMapper.toResponse(insumo);
    }

    public List<InsumoResponse> findByNameContainingIgnoreCase(String name) {
        List<Insumo> insumos = insumoRepository.findByNombreContainingIgnoreCase(name);

        if (insumos.isEmpty()) {
            throw new EntityNotFoundException("No Insumos found with name containing: " + name);
        }

        return insumos.stream()
                .map(insumoMapper::toResponse)
                .toList();
    }

    public List<InsumoResponse> getAllInsumos() {

        List<Insumo> insumos = insumoRepository.findAll();

        if (insumos.isEmpty()) {
            throw new EntityNotFoundException("No Insumos found");
        }

        return insumos.stream()
                .map(insumoMapper::toResponse)
                .toList();
    }
}
