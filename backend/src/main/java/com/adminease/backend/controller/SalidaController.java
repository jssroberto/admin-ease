package com.adminease.backend.controller;

import com.adminease.backend.api.dto.request.SalidaRequest;
import com.adminease.backend.api.dto.response.SalidaResponse;
import com.adminease.backend.service.SalidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salida")
@RequiredArgsConstructor
@Tag(name = "Salida", description = "Operations related to Salida")
public class SalidaController {

    private final SalidaService salidaService;

    @PostMapping
    @Operation(
            summary = "Create a new Salida",
            description = "Creates a new Salida entry in the system and returns the created object."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Salida created successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<SalidaResponse> createSalida(@Valid @RequestBody SalidaRequest request) {
        SalidaResponse response = salidaService.createSalida(request);

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(response);
    }

    @GetMapping
    @Operation(
            summary = "Get all Salidas",
            description = "Retrieves a list of all Salida entries in the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Salidas retrieved successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<SalidaResponse>> getAllSalidas() {
        List<SalidaResponse> responses = salidaService.getAllSalidas();
        return ResponseEntity.ok(responses);
    }
}
