package com.adminease.backend.repository;

import com.adminease.backend.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {

    // Buscar permiso por nombre (ignorando mayúsculas/minúsculas)
    Permiso findByNombreIgnoreCase(String nombre);

    // Buscar todos los permisos asociados a un rol específico
    List<Permiso> findByRoles_Id(Long rolId);
}
