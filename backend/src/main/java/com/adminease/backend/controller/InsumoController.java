package com.adminease.backend.controller;

import com.adminease.backend.api.dto.request.InsumoRequest;
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

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/insumo")
@RequiredArgsConstructor
@Tag(name = "Insumo", description = "Operations related to Insumo")
public class InsumoController {

    private final InsumoService insumoService;

    @GetMapping
    @Operation(summary = "Get all Insumos",
            description = "Retrieves a list of all Insumos in the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Insumos retrieved successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Insumos not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<InsumoResponse>> getAllInsumos() {
        List<InsumoResponse> responses = insumoService.getAllInsumos();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    @Operation(summary = "Search Insumos",
            description = "Search Insumos by name containing the query string")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Insumos found successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<InsumoResponse>> searchInsumos(@RequestParam String query) {
        List<InsumoResponse> responses = insumoService.findByNameContainingIgnoreCase(query);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Insumo by ID", description = "Retrieves an Insumo by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Insumo retrieved successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Insumo not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<InsumoResponse> getInsumoById(@PathVariable Long id) {
        InsumoResponse response = insumoService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Create a new Insumo", description = "Creates a new Insumo in the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Insumo created successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<InsumoResponse> createInsumo(@RequestBody InsumoRequest insumoRequest) {
        InsumoResponse createdInsumo = insumoService.createInsumo(insumoRequest);
        URI location = URI.create("/api/v1/insumo/" + createdInsumo.getId());
        return ResponseEntity.created(location).body(createdInsumo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Insumo",
            description = "Updates an existing Insumo by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Insumo updated successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Insumo not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<InsumoResponse> updateInsumo(@PathVariable Long id,
            @RequestBody InsumoRequest insumoRequest) {
        InsumoResponse updatedInsumo = insumoService.updateInsumo(id, insumoRequest);
        return ResponseEntity.ok(updatedInsumo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an Insumo", description = "Deletes an Insumo by its ID.")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Insumo deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Insumo not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteInsumo(@PathVariable Long id) {
        insumoService.deleteInsumo(id);
        return ResponseEntity.noContent().build();
    }
}
