package com.adminease.backend.repository;

import com.adminease.backend.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    List<Proveedor> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);
}
