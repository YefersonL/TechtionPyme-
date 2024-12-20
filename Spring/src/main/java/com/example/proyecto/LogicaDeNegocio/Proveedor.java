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
public class Proveedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;

    @OneToMany
    private List<ProductoInventario> productosDisponibles;

    public void listarProductos() {
        // Lógica para listar productos
    }

    public void actualizarPrecio(ProductoInventario producto, double nuevoPrecio) {
        // Lógica para actualizar precio
    }

    public void gestionarPedidosProveedor() {
        // Lógica para gestionar pedidos con el proveedor
    }

    public void mostrarInfoProveedor() {
        // Lógica para mostrar información del proveedor
    }
}