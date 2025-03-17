package com.adminease.backend.repository;

import com.adminease.backend.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    // Buscar compras por proveedor
    List<Compra> findByProveedor_ProveedorId(Long proveedorId);

    // Buscar compras por usuario
    List<Compra> findByUsuario_UsuarioId(Long usuarioId);
}
