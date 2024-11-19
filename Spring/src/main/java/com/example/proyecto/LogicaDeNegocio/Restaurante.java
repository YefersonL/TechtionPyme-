/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Restaurante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany
    private List<Empleado> empleados;

    @OneToMany
    private List<Proveedor> proveedores;

    @OneToMany
    private List<Pedido> pedidos;



    public void realizarPedido(ProductoInventario producto, int cantidad) {
        // Lógica para realizar pedido
    }

    public void gestionarStock() {
        // Lógica para gestionar stock
    }

    public void gestionarFacturas() {
        // Lógica para gestionar facturas
    }

    public void balancearFactura() {
        // Lógica para balancear factura
    }
}