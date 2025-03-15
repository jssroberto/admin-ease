package com.adminease.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "permisos")
    private List<Rol> roles;
}
