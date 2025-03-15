package com.adminease.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insumoId;

    private String codigo;
    private String nombre;
    private String unidadMedida;
    private Integer stock;
    private String categoria;

    @OneToMany(mappedBy = "insumo")
    private List<InsumosProducto> productosInsumo;

    @OneToMany(mappedBy = "insumo")
    private List<MovimientoInsumo> movimientosInsumo;
}
