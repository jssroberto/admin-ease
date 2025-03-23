package com.adminease.backend.controller;

import com.adminease.backend.api.dto.response.InsumoResponse;
import com.adminease.backend.service.InsumoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insumo")
@RequiredArgsConstructor
@Tag(name = "Insumo", description = "Insumo Controller")
@CrossOrigin(origins = "*")
public class InsumoController {

    private final InsumoService insumoService;

    @GetMapping
    @Operation(summary = "Search Insumos")
    public ResponseEntity<List<InsumoResponse>> searchInsumos(@RequestParam String query) {
        List<InsumoResponse> responses = insumoService.findByName(query);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Insumo by ID")
    public ResponseEntity<InsumoResponse> getInsumoById(@PathVariable Long id) {
        InsumoResponse response = insumoService.findById(id);
        return ResponseEntity.ok(response);
    }
}
