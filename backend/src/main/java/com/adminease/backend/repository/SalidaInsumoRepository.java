package com.adminease.backend.repository;

import com.adminease.backend.model.SalidaInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalidaInsumoRepository extends JpaRepository<SalidaInsumo, Long> {

    // Buscar todos los insumos relacionados con una salida específica
    List<SalidaInsumo> findBySalida_SalidaId(Long salidaId);

    // Buscar todos los insumos relacionados con un movimiento de insumo específico
    List<SalidaInsumo> findByMovimientoInsumo_MovimientoInsumoId(Long movimientoInsumoId);
}
