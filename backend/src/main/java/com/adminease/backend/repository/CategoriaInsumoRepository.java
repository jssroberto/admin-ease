package com.adminease.backend.repository;

import com.adminease.backend.model.CategoriaInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaInsumoRepository extends JpaRepository<CategoriaInsumo, Long> {
}
