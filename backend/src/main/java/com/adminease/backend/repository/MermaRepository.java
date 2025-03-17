package com.adminease.backend.repository;

import com.adminease.backend.model.Merma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MermaRepository extends JpaRepository<Merma, Long> {

    // Buscar todas las mermas registradas por un usuario específico
    List<Merma> findByUsuario_UsuarioId(Long usuarioId);
}
