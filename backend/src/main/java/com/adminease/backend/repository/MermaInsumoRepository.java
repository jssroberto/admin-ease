package com.adminease.backend.repository;

import com.adminease.backend.model.MermaInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MermaInsumoRepository extends JpaRepository<MermaInsumo, Long> {

    // Buscar todos los insumos de una merma específica
    List<MermaInsumo> findByMerma_MermaId(Long mermaId);

    // Buscar todas las mermas relacionadas con un movimiento de insumo específico
    List<MermaInsumo> findByMovimientoInsumo_MovimientoInsumoId(Long movimientoInsumoId);
}
