package com.adminease.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adminease.backend.model.CompraInsumo;

@Repository
public interface CompraInsumoRepository extends JpaRepository<CompraInsumo, Long> {

    public List<CompraInsumo> findByCompraId(Long compra);
}
