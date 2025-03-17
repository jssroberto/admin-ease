package com.adminease.backend.repository;

import com.adminease.backend.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {

    // Buscar insumos por nombre
    List<Insumo> findByNombreContainingIgnoreCase(String nombre);

    // Buscar insumos por categoría
    List<Insumo> findByCategoria(String categoria);

    // Buscar insumos con stock menor a cierto valor
    List<Insumo> findByStockLessThan(Integer stock);
}
