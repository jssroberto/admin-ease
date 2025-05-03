package com.adminease.backend.service;

import com.adminease.backend.api.dto.request.CompraInsumoRequest;
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
    private final InsumoRepository insumoRepository;

    public List<CompraInsumo> createCompraInsumos(List<CompraInsumoRequest> insumoRequests, Compra compra) {
        List<CompraInsumo> compraInsumos = new ArrayList<>();

        for (CompraInsumoRequest req : insumoRequests) {
            Insumo insumo = insumoRepository.findById(req.getInsumoId())
                    .orElseThrow(() -> new EntityNotFoundException("Insumo  no encontrado"));

            CompraInsumo compraInsumo = new CompraInsumo();
            compraInsumo.setInsumo(insumo);
            compraInsumo.setCantidad(req.getCantidad());
            compraInsumo.setPrecioUnitario(req.getPrecioUnitario());
            compraInsumo.setCompra(compra);

            compraInsumos.add(compraInsumo);
        }

        return compraInsumoRepository.saveAll(compraInsumos);
    }
}
