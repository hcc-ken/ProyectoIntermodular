package com.taller.taller.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="coche")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Coche {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @Column(name="matricula", nullable = false, unique = true, length = 50)
    private String matricula;

    @Column(name="color", nullable = false, length = 50)
    private String color;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Cliente cliente;

    public Coche(String modelo, String matricula, String color, Cliente cliente) {
        this.modelo = modelo;
        this.matricula = matricula;
        this.color = color;
        this.cliente = cliente;
    }
}
