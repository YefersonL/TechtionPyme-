/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.Services;

import com.example.proyecto.LogicaDeNegocio.ProductoInventario;
import com.example.proyecto.Persistencia.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoInventarioService {

    @Autowired
    private ProductoRepository productoRepository;

    // Crear producto
    public ProductoInventario createProducto(ProductoInventario producto) {

        return productoRepository.save(producto);
    }

    // Obtener todos los productos
    public List<ProductoInventario> getAllProductos() {
        return productoRepository.findAll();
    }

    // Obtener producto por ID
    public Optional<ProductoInventario> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    // Actualizar producto
    public ProductoInventario updateProducto(Long id, ProductoInventario productoModificado) {
        ProductoInventario productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productoExistente.setNombre(productoModificado.getNombre());
        productoExistente.setPrecio(productoModificado.getPrecio());
        productoExistente.setCantidad(productoModificado.getCantidad());

        return productoRepository.save(productoExistente);
    }

    // Eliminar producto
    public void deleteProducto(Long id) {
        ProductoInventario producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        productoRepository.delete(producto);
    }
}