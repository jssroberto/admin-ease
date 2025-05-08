package com.adminease.backend.controller;

import com.adminease.backend.api.dto.request.ProveedorRequest;
import com.adminease.backend.api.dto.response.ProveedorResponse;
import com.adminease.backend.service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedor")
@RequiredArgsConstructor
@Tag(name = "Proveedor", description = "Operaciones relacionadas con los proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    @Operation(summary = "Obtener todos los proveedores")
    public ResponseEntity<List<ProveedorResponse>> getAllProveedores() {
        List<ProveedorResponse> proveedores = proveedorService.getAllProveedores();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar proveedor por id")
    public ResponseEntity<ProveedorResponse> getProveedorById(@PathVariable Long id) {
        ProveedorResponse proveedor = proveedorService.findById(id);
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping
    @Operation(summary = "Crear proveedor")
    public ResponseEntity<ProveedorResponse> createProveedor(
            @RequestBody @Valid ProveedorRequest proveedorRequest) {
        ProveedorResponse creado = proveedorService.create(proveedorRequest);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proveedor")
    public ResponseEntity<ProveedorResponse> updateProveedor(
            @PathVariable Long id,
            @RequestBody @Valid ProveedorRequest proveedorRequest) {
        ProveedorResponse actualizado = proveedorService.update(id, proveedorRequest);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar proveedor por id")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Long id) {
        proveedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
