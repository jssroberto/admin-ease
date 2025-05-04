package com.adminease.backend.service;

import com.adminease.backend.api.dto.request.ProveedorRequest;
import com.adminease.backend.api.dto.response.ProveedorResponse;
import com.adminease.backend.mapper.ProveedorMapper;
import com.adminease.backend.model.Proveedor;
import com.adminease.backend.repository.ProveedorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorMapper proveedorMapper;
    private final ProveedorRepository proveedorRepository;

    public List<ProveedorResponse> getAllProveedores() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        return proveedores.stream()
                .map(proveedorMapper::toResponse)
                .toList();
    }

    public ProveedorResponse findById(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado"));
        return proveedorMapper.toResponse(proveedor);
    }

    public ProveedorResponse create(ProveedorRequest proveedor) {
        Proveedor proveedorEntity = proveedorMapper.toEntity(proveedor);
        proveedorEntity = proveedorRepository.save(proveedorEntity);
        return proveedorMapper.toResponse(proveedorEntity);
    }

    public ProveedorResponse update(Long id, ProveedorRequest proveedor) {
        proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado para actualizar"));

        Proveedor actualizado = proveedorMapper.toEntity(proveedor);
        actualizado.setId(id);

        actualizado = proveedorRepository.save(actualizado);
        return proveedorMapper.toResponse(actualizado);
    }


    public ProveedorResponse delete(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado para eliminar"));
        proveedorRepository.deleteById(id);
        return proveedorMapper.toResponse(proveedor);
    }
}