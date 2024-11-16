/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.*;
import java.util.List;

@Entity
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

    public Restaurante(String nombre, List<Empleado> empleados, List<Proveedor> proveedores, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.empleados = empleados;
        this.proveedores = proveedores;
        this.pedidos = pedidos;
    }

    public Restaurante() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void realizarPedido(Producto producto, int cantidad) {
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