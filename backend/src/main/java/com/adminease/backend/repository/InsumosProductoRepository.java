package com.adminease.backend.repository;

import com.adminease.backend.model.InsumosProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InsumosProductoRepository extends JpaRepository<InsumosProducto, Long> {

    // Buscar todos los insumos usados en un producto específico
    List<InsumosProducto> findByProducto_ProductoId(Long productoId);

    // Buscar todos los productos en los que se usa un insumo específico
    List<InsumosProducto> findByInsumo_InsumoId(Long insumoId);
}
