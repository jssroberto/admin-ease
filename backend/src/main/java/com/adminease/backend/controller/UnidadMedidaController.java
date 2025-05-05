package com.adminease.backend.controller;

import com.adminease.backend.api.dto.response.UnidadMedidaResponse;
import com.adminease.backend.service.UnidadMedidaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/unidadMedida")
@RequiredArgsConstructor
@Tag(name = "UnidadMedida", description = "Operations related to Unidad Medida")
@CrossOrigin(origins = "*")
public class UnidadMedidaController {

    private final UnidadMedidaService unidadMedidaService;

    @GetMapping
    @Operation(
            summary = "Get all Unidades de Medida",
            description = "Retrieves a list of all Units of measure in the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidades de Medida retrieved successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<UnidadMedidaResponse>> getAllUnidadesMedida() {
        List<UnidadMedidaResponse> responses = unidadMedidaService.getAllUnidadesMeedida();
        return ResponseEntity.ok(responses);
    }

}
