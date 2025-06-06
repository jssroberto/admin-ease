package com.adminease.backend.repository;

import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adminease.backend.model.Compra;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    List<Compra> findByFechaBetween(ZonedDateTime fechaInicial, ZonedDateTime fechaFinal);

    List<Compra> findByProveedorId(Long proveedorId);

    List<Compra> findByProveedorIdAndFechaBetween(Long proveedorId, ZonedDateTime fechaInicial,
            ZonedDateTime fechaFinal);

    List<Compra> findByTotalGreaterThan(Double minimo);

    List<Compra> findByTotalLessThan(Double maximo);

    ZonedDateTime fecha(@NotNull @PastOrPresent ZonedDateTime fecha);
}
