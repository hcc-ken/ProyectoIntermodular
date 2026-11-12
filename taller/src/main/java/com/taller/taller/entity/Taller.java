package com.taller.taller.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="taller")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Taller {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", length = 25, nullable = false)
    private String nombre;

    @Column(name="calle", length = 250, nullable = false)
    private String calle;

    @Column(name = "nombre_encargado", length = 250, nullable = false)
    private String nombreEncargado;

    @OneToMany(mappedBy = "taller", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Cliente> clientes = new ArrayList<>();

    public Taller(String nombre, String calle, String nombreEncargado) {
        this.nombre = nombre;
        this.calle = calle;
        this.nombreEncargado = nombreEncargado;
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
        cliente.setTaller(this);
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        if (clientes != null) {
            for (Cliente cliente : clientes) {
                cliente.setTaller(this);
            }
        }
    }
}
