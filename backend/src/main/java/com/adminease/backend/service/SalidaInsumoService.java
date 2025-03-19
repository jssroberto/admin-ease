package com.adminease.backend.service;

import com.adminease.backend.api.dto.request.SalidaInsumoRequest;
import com.adminease.backend.api.dto.response.SalidaInsumoResponse;
import com.adminease.backend.mapper.SalidaInsumoMapper;
import com.adminease.backend.model.Insumo;
import com.adminease.backend.model.Salida;
import com.adminease.backend.model.SalidaInsumo;
import com.adminease.backend.repository.InsumoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalidaInsumoService {

    private final InsumoRepository insumoRepository;
    private final SalidaInsumoMapper salidaInsumoMapper;


    public List<SalidaInsumoResponse> createSalidaInsumos(Salida salida, List<SalidaInsumoRequest> requests) {
        List<SalidaInsumo> salidaInsumos = requests.stream().map(request -> {
            Insumo insumo = getInsumoById(request.getInsumoId());
            SalidaInsumo salidaInsumo = salidaInsumoMapper.toEntity(request);
            salidaInsumo.setInsumo(insumo);
            salidaInsumo.setSalida(salida);
            return salidaInsumo;
        }).toList();

        return salidaInsumos.stream().map(salidaInsumoMapper::toResponse).toList();
    }

    private Insumo getInsumoById(Long insumoId) {
        return insumoRepository.findById(insumoId)
                .orElseThrow(() -> new EntityNotFoundException("Insumo not found with ID: " + insumoId));
    }
}
