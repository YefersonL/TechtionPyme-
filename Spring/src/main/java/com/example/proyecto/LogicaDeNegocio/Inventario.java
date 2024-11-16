/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Inventarios")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Corregido a Long para mantener consistencia

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "inventario_id") // Asociar la clave foránea en la tabla de productos
    private List<Producto> productos;

    // Constructor
    public Inventario() {
        this.productos = new ArrayList<>();
    }

    // Método para agregar un producto al inventario
    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }

    // Método para eliminar un producto del inventario
    public void eliminarProducto(Producto producto) {
        this.productos.remove(producto);
    }

    // Método para modificar un producto en el inventario
    public void modificarProducto(Producto productoModificado) {
    for (Producto producto : productos) {
        if (producto.getId().equals(productoModificado.getId())) {
            producto.setNombre(productoModificado.getNombre());
            producto.setPrecio(productoModificado.getPrecio());
            producto.setCantidad(productoModificado.getCantidad());
            break;
        }
    }
}

public void actualizarStock(Long productoId, int nuevaCantidad) {
    for (Producto producto : productos) {
        if (producto.getId().equals(productoId)) {
            producto.setCantidad(nuevaCantidad);
            break;
        }
    }
}

    // Getter y Setter para la lista de productos
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    // Getter y Setter para el ID del inventario
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}