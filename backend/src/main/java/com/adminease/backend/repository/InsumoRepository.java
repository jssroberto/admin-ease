package com.adminease.backend.repository;

import com.adminease.backend.model.CategoriaInsumo;
import com.adminease.backend.model.Insumo;
import com.adminease.backend.model.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {

    Optional<Insumo> findByCodigo(String codigo);

    @Query("SELECT i FROM Insumo i WHERE unaccent(LOWER(i.nombre)) LIKE unaccent(LOWER(concat('%', :nombre, '%')))")
    List<Insumo> findByNombreContainingIgnoreCaseAndAccents(@Param("nombre") String nombre);

    List<Insumo> findByStockGreaterThan(Double stock);

    List<Insumo> findByStockLessThan(Double stock);

    List<Insumo> findByCategoriaInsumo(CategoriaInsumo categoriaInsumo);

}
