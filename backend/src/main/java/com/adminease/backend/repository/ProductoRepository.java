package com.adminease.backend.repository;

import com.adminease.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Buscar productos por nombre (ignora mayúsculas/minúsculas)
    List<Producto> findByNombreIgnoreCase(String nombre);

    // Buscar productos por categoría
    List<Producto> findByCategoria(String categoria);
}
