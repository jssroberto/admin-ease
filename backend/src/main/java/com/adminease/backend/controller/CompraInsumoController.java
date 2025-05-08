package com.adminease.backend.controller;

import com.adminease.backend.api.dto.request.CompraInsumoRequest;
import com.adminease.backend.api.dto.response.CompraInsumoResponse;
import com.adminease.backend.mapper.CompraInsumoMapper;
import com.adminease.backend.model.CompraInsumo;
import com.adminease.backend.service.CompraInsumoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/compra-insumo")
@RequiredArgsConstructor
@Tag(name = "Compra de Insumos", description = "Operaciones relacionadas con compras de insumos")
public class CompraInsumoController {

    private final CompraInsumoService compraInsumoService;
    private final CompraInsumoMapper compraInsumoMapper;


    @GetMapping("/{id}")
    @Operation(summary = "Obtener insumo de compra por id")
    public ResponseEntity<CompraInsumoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(compraInsumoService.getCompraInsumo(id));
    }

    @PostMapping("/compra/{compraId}")
    @Operation(summary = "Crear insumo de compra")
    public ResponseEntity<List<CompraInsumoResponse>> createInsumos(
            @PathVariable Long compraId,
            @RequestBody List<CompraInsumoRequest> requests) {

        List<CompraInsumo> createdList = compraInsumoService.createCompraInsumos(requests, compraId);

        List<CompraInsumoResponse> responseList = createdList.stream()
                .map(compraInsumoMapper::toResponse)
                .toList();

        return new ResponseEntity<>(responseList, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar insumo de compra")
    public ResponseEntity<CompraInsumoResponse> update(
            @PathVariable Long id,
            @RequestBody CompraInsumoRequest request) {
        return ResponseEntity.ok(compraInsumoService.updateCompraInsumo(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar insumo de compra")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        compraInsumoService.deleteCompraInsumo(id);
        return ResponseEntity.noContent().build();
    }
}
