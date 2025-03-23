package com.adminease.backend.controller;

import com.adminease.backend.api.dto.response.InsumoResponse;
import com.adminease.backend.service.InsumoService;
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
@RequestMapping("/api/v1/insumo")
@RequiredArgsConstructor
@Tag(name = "Insumo", description = "Operations related to Insumo")
@CrossOrigin(origins = "*")
public class InsumoController {

    private final InsumoService insumoService;

    @GetMapping("/search")
    @Operation(
            summary = "Search Insumos",
            description = "Search Insumos by name containing the query string"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Insumos found successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<InsumoResponse>> searchInsumos(@RequestParam String query) {
        List<InsumoResponse> responses = insumoService.findByNameContainingIgnoreCase(query);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsumoResponse> getInsumoById(@PathVariable Long id) {
        InsumoResponse response = insumoService.findById(id);
        return ResponseEntity.ok(response);
    }
}
