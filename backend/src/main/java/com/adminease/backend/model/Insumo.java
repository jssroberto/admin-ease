package com.adminease.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 12, max = 13)
    @Column(nullable = false, unique = true)
    private String codigo;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nombre;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Double stock;

    @NotNull
    @ManyToOne
    private UnidadMedida unidadMedida;

    @NotNull
    @ManyToOne
    private CategoriaInsumo categoriaInsumo;

    @OneToMany(mappedBy = "insumo")
    private List<InsumosProducto> insumosProductos;

    @OneToMany(mappedBy = "insumo")
    private List<MovimientoInsumo> movimientoInsumos;

}
