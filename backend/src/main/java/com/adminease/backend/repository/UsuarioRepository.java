package com.adminease.backend.repository;

import com.adminease.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por nombre (ignorando mayúsculas/minúsculas)
    Usuario findByNombreIgnoreCase(String nombre);

    // Buscar usuarios por rol
    List<Usuario> findByRol_Id(Long rolId);
}
