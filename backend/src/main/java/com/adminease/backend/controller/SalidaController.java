package com.adminease.backend.controller;

import com.adminease.backend.api.dto.request.SalidaRequest;
import com.adminease.backend.api.dto.response.SalidaResponse;
import com.adminease.backend.dto.SalidaDTO;
import com.adminease.backend.service.SalidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salida")
@RequiredArgsConstructor
@Tag(name = "Salida", description = "Salida Controller")
public class SalidaController {

    private final SalidaService salidaService;

    @PostMapping
    @Operation (summary = "Create a new Salida")
    @ResponseStatus (code = HttpStatus.CREATED)
    public ResponseEntity<SalidaResponse> createSalida(@Valid @RequestBody SalidaRequest request) {
        SalidaResponse response = salidaService.createSalida(request);

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(response);
    }
}
