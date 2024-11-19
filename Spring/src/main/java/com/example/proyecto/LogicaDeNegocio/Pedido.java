/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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



    public Pedido(String descripcion, Mesero mesero) {
        this.descripcion = descripcion;
        this.mesero = mesero;
        this.estado = "EN_ESPERA";
    }
}