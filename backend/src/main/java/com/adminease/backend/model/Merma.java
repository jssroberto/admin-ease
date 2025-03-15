package com.adminease.backend.model;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.List;

@Entity
public class Merma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mermaId;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "merma")
    private List<MermaInsumo> mermaInsumo;
}
