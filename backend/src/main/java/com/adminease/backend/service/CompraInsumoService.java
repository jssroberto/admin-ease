package com.adminease.backend.service;

import com.adminease.backend.api.dto.request.CompraInsumoRequest;
import com.adminease.backend.api.dto.response.CompraInsumoResponse;
import com.adminease.backend.mapper.CompraInsumoMapper;
import com.adminease.backend.model.Compra;
import com.adminease.backend.model.CompraInsumo;
import com.adminease.backend.model.Insumo;
import com.adminease.backend.repository.CompraInsumoRepository;
import com.adminease.backend.repository.CompraRepository;
import com.adminease.backend.repository.InsumoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraInsumoService {

    private final CompraInsumoRepository compraInsumoRepository;
    private final CompraRepository compraRepository;
    private final InsumoRepository insumoRepository;
    private final CompraInsumoMapper compraInsumoMapper;

    public List<CompraInsumo> createCompraInsumos(List<CompraInsumoRequest> insumoRequests, Long compraId) {
        Compra compra = compraRepository.findById(compraId)
                .orElseThrow(() -> new EntityNotFoundException("Compra no encontrada"));

        List<CompraInsumo> result = new ArrayList<>();

        for (CompraInsumoRequest insumoRequest : insumoRequests) {
            Insumo insumo = insumoRepository.findById(insumoRequest.getInsumoId())
                    .orElseThrow(() -> new EntityNotFoundException("Insumo con ID " + insumoRequest.getInsumoId() + " no encontrado"));

            CompraInsumo compraInsumo = new CompraInsumo();
            compraInsumo.setCompra(compra);
            compraInsumo.setInsumo(insumo);
            compraInsumo.setCantidad(insumoRequest.getCantidad());
            compraInsumo.setPrecioUnitario(insumoRequest.getPrecioUnitario());

            compraInsumo = compraInsumoRepository.save(compraInsumo);
            result.add(compraInsumo);
        }

        return result;
    }




    public CompraInsumoResponse getCompraInsumo(Long id) {
        CompraInsumo compra = compraInsumoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compra no encontrado para actualizar"));
        return compraInsumoMapper.toResponse(compra);

    }

    public CompraInsumoResponse deleteCompraInsumo(Long id) {
        CompraInsumo compraInsumo = compraInsumoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compra no encontrado para actualizar"));

        compraInsumoRepository.delete(compraInsumo);

        return compraInsumoMapper.toResponse(compraInsumo);

    }

    public CompraInsumoResponse updateCompraInsumo(Long id,CompraInsumoRequest compraInsumoRequest) {
        compraInsumoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compra no encontrado para actualizar"));

        CompraInsumo compraInsumo = compraInsumoMapper.toEntity(compraInsumoRequest);
        compraInsumo = compraInsumoRepository.save(compraInsumo);

        return compraInsumoMapper.toResponse(compraInsumo);
    }

    public List<CompraInsumoResponse> getCompraInsumoByCompraId(Long id) {
        if (!compraRepository.existsById(id)) {
            throw new EntityNotFoundException("Compra no encontrada");
        }

        List<CompraInsumo> compraInsumos = compraInsumoRepository.findByCompraId(id);

        return compraInsumos.stream()
                .map(compraInsumoMapper::toResponse)
                .toList();
    }

}

