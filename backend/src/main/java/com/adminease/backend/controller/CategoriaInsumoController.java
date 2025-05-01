package com.adminease.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adminease.backend.api.dto.response.CategoriaInsumoResponse;
import com.adminease.backend.service.CategoriaInsumoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categoriaInsumo")
@RequiredArgsConstructor
@Tag(name = "Categoria Insumo", description = "Operations related to CategoriaInsumo")
@CrossOrigin(origins = "*")
public class CategoriaInsumoController {

    private final CategoriaInsumoService categoriaInsumoService;

    @GetMapping
    @Operation(
            summary = "Get all Categorias Insumo",
            description = "Retrieves a list of all Insumo Categories in the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categorias  retrieved successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<CategoriaInsumoResponse>> getAllCategoriasInsumo() {
        List<CategoriaInsumoResponse> responses = categoriaInsumoService.getAllCategorias();
        return ResponseEntity.ok(responses);
    }

}
