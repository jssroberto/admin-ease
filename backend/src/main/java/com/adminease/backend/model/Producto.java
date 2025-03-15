package com.adminease.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;

    private String nombre;
    private String categoria;

    @OneToMany(mappedBy = "producto")
    private List<InsumosProducto> insumosProductos;
}
