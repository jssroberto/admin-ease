package com.adminease.backend.repository;

import com.adminease.backend.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    // Buscar rol por nombre (ignorando mayúsculas/minúsculas)
    Rol findByNameIgnoreCase(String name);

    // Buscar roles asignados a un usuario específico
    List<Rol> findByUsuarios_Id(Long usuarioId);
}
