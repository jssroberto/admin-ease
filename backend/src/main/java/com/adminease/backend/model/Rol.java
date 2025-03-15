package com.adminease.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;

    @ManyToMany
    @JoinTable(
            name = "permisos_rol",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "permiso_id")
    )
    private List<Permiso> permisos;




}
