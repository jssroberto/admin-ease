package com.adminease.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compraId;

    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proovedor proovedor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "compra")
    private List<CompraInsumo> compraInsumos;

}
