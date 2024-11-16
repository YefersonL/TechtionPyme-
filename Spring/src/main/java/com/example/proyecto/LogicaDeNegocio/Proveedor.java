/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Proveedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;

    @OneToMany
    private List<Producto> productosDisponibles;

    public Proveedor(String nombre, List<Producto> productosDisponibles) {
        this.nombre = nombre;
        this.productosDisponibles = productosDisponibles;
    }

    public Proveedor() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

    public void setProductosDisponibles(List<Producto> productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
    }

    public void listarProductos() {
        // Lógica para listar productos
    }

    public void actualizarPrecio(Producto producto, double nuevoPrecio) {
        // Lógica para actualizar precio
    }

    public void gestionarPedidosProveedor() {
        // Lógica para gestionar pedidos con el proveedor
    }

    public void mostrarInfoProveedor() {
        // Lógica para mostrar información del proveedor
    }
}