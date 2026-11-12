package com.taller.taller.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Cliente {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", length = 250, nullable = false)
    private String nombre;

    @Column(name="telefono", unique = true, nullable = false)
    private String telefono;

    @Column(name = "codigo_postal", length = 250, nullable = false)
    private String codigo_postal;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="coche_id", referencedColumnName = "id")
    private Coche coche;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taller_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Taller taller;

    public Cliente(String nombre, String telefono, String codigo_postal, Coche coche, Taller taller) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.codigo_postal = codigo_postal;
        this.coche = coche;
        this.taller = taller;
    }
}
