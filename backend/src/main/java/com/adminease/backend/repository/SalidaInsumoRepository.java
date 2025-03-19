package com.adminease.backend.repository;

import com.adminease.backend.model.SalidaInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalidaInsumoRepository extends JpaRepository<SalidaInsumo, Long> {

    List<SalidaInsumo> findByInsumoId(Long insumoId);

}
