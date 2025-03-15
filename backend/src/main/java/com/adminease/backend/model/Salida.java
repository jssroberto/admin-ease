package com.adminease.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Salida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salidaId;

    private String area;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "salida")
    private List<SalidaInsumo> salidaInsumo;
}
