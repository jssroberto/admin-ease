package com.adminease.backend.repository;

import com.adminease.backend.model.Salida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SalidaRepository extends JpaRepository<Salida, Long> {

    // Buscar todas las salidas realizadas por un usuario específico
    List<Salida> findByUsuario_UsuarioId(Long usuarioId);

    // Buscar salidas por área
    List<Salida> findByArea(String area);
}
