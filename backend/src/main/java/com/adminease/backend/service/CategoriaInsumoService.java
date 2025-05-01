package com.adminease.backend.service;

import com.adminease.backend.api.dto.response.CategoriaInsumoResponse;
import com.adminease.backend.mapper.CategoriaInsumoMapper;
import com.adminease.backend.model.CategoriaInsumo;
import com.adminease.backend.repository.CategoriaInsumoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaInsumoService {

    private final CategoriaInsumoRepository categoriaInsumoRepository;
    private final CategoriaInsumoMapper categoriaInsumoMapper;

    public CategoriaInsumoResponse getCategoriaById(Long id) {
        Optional<CategoriaInsumo> categoria = categoriaInsumoRepository.findById(id);

        if (categoria.isEmpty()) {
            throw new RuntimeException("CategoriaInsumo not found with ID: " + id);
        }

        return new CategoriaInsumoResponse(
                categoria.get().getId(),
                categoria.get().getNombre());
    }

    public List<CategoriaInsumoResponse> getAllCategorias() {
        List<CategoriaInsumo> categorias = categoriaInsumoRepository.findAll();

        if (categorias.isEmpty()) {
            throw new RuntimeException("No categorias found");
        }

        return categorias.stream()
                .map(categoriaInsumoMapper::toResponse)
                .toList();
    }
}