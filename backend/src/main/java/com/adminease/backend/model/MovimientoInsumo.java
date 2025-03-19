package com.adminease.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_movimiento")
@Entity
public abstract class MovimientoInsumo {

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

}
