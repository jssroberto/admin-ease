package com.adminease.backend.controller;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.adminease.backend.api.dto.request.CompraRequest;
import com.adminease.backend.api.dto.response.CompraResponse;
import com.adminease.backend.service.CompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/compra")
@RequiredArgsConstructor
@Tag(name = "Compra", description = "Operaciones relacionadas con las Compras")
public class CompraController {

    private final CompraService compraService;

    @GetMapping
    @Operation(summary = "Listar todas las compras")
    public ResponseEntity<List<CompraResponse>> getAllCompras() {
        return ResponseEntity.ok(compraService.getAllCompras());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una compra por ID")
    public ResponseEntity<CompraResponse> getCompraById(@PathVariable Long id) {
        return ResponseEntity.ok(compraService.findById(id));
    }

    @GetMapping("/proveedor/{proveedorId}")
    @Operation(summary = "Buscar compras por proveedor")
    public ResponseEntity<List<CompraResponse>> getByProveedor(@PathVariable Long proveedorId) {
        return ResponseEntity.ok(compraService.findByProveedorId(proveedorId));
    }

    @GetMapping("/fecha")
    @Operation(summary = "Buscar compras por rango de fechas")
    public ResponseEntity<List<CompraResponse>> getByFecha(@RequestParam ZonedDateTime desde,
            @RequestParam ZonedDateTime hasta) {
        return ResponseEntity.ok(compraService.findByFechaBetween(desde, hasta));
    }

    @GetMapping("/proveedor/{proveedorId}/fecha")
    @Operation(summary = "Buscar compras por proveedor y rango de fechas")
    public ResponseEntity<List<CompraResponse>> getByProveedorAndFecha(
            @PathVariable Long proveedorId, @RequestParam ZonedDateTime desde,
            @RequestParam ZonedDateTime hasta) {
        return ResponseEntity
                .ok(compraService.findByProveedorIdAndFechaBetween(proveedorId, desde, hasta));
    }

    @GetMapping("/total/minimo")
    @Operation(summary = "Buscar compras con total mayor que un mínimo")
    public ResponseEntity<List<CompraResponse>> getByTotalMayor(@RequestParam Double minimo) {
        return ResponseEntity.ok(compraService.findByTotalGreaterThan(minimo));
    }

    @GetMapping("/total/maximo")
    @Operation(summary = "Buscar compras con total menor que un máximo")
    public ResponseEntity<List<CompraResponse>> getByTotalMenor(@RequestParam Double maximo) {
        return ResponseEntity.ok(compraService.findByTotalLessThan(maximo));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva compra")
    public ResponseEntity<CompraResponse> createCompra(@RequestBody CompraRequest request) {
        CompraResponse created = compraService.createCompra(request);
        URI location = URI.create("/api/v1/compra/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una compra")
    public ResponseEntity<CompraResponse> update(@PathVariable Long id,
            @RequestBody CompraRequest request) {
        return ResponseEntity.ok(compraService.updateCompra(id, request));
    }

    @PutMapping("/updateTotal/{id}")
    @Operation(summary = "Actualizar el total de una compra después de modificar insumos")
    public ResponseEntity<Void> updateTotal(@PathVariable Long id) {
        compraService.updateTotalCompra(id);
        return ResponseEntity.noContent().build();
    }



    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar compra por id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        compraService.deleteCompra(id);
        return ResponseEntity.noContent().build();
    }
}

