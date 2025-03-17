package com.adminease.backend.repository;

import com.adminease.backend.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    // Buscar proveedor por nombre (ignorando mayúsculas/minúsculas)
    Proveedor findByNombreIgnoreCase(String nombre);

    // Buscar proveedores por correo
    List<Proveedor> findByCorreo(String correo);
}
