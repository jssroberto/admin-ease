package com.adminease.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InsumosProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double cantidad;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "insumo_id")
    private Insumo insumo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;


}
