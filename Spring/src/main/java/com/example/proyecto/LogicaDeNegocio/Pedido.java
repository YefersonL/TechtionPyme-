/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.*;

@Entity
@Table(name = "Pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String estado; // "EN_ESPERA", "LISTO", "ENTREGADO"

    @ManyToOne
    @JoinColumn(name = "mesero_id", nullable = true)
    private Mesero mesero; // Relación con el mesero que creó el pedido

    @ManyToOne
    @JoinColumn(name = "cocinero_id", nullable = true)
    private Cocinero cocinero; // Relación con el cocinero asignado al pedido

    public Pedido() {
    }

    public Pedido(String descripcion, Mesero mesero) {
        this.descripcion = descripcion;
        this.mesero = mesero;
        this.estado = "EN_ESPERA";
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Mesero getMesero() {
        return mesero;
    }

    public void setMesero(Mesero mesero) {
        this.mesero = mesero;
    }

    public Cocinero getCocinero() {
        return cocinero;
    }

    public void setCocinero(Cocinero cocinero) {
        this.cocinero = cocinero;
    }
}