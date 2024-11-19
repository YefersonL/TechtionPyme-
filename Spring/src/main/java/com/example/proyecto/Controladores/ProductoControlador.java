/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.Controladores;

import com.example.proyecto.LogicaDeNegocio.ProductoInventario;
import com.example.proyecto.Services.ProductoInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoControlador {

    @Autowired
    private ProductoInventarioService productoInventarioService;

    // Crear producto
    @PostMapping
    public ResponseEntity<ProductoInventario> createProducto(@RequestBody ProductoInventario producto) {
        ProductoInventario nuevoProductoInventario = productoInventarioService.createProducto(producto);
        return new ResponseEntity<>(nuevoProductoInventario, HttpStatus.CREATED);
    }

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductoInventario>> getAllProductos() {
        List<ProductoInventario> productos = productoInventarioService.getAllProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoInventario> getProductoById(@PathVariable Long id) {
        return productoInventarioService.getProductoById(id)
                .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductoInventario> updateProducto(@PathVariable Long id, @RequestBody ProductoInventario producto) {
        try {
            ProductoInventario productoActualizado = productoInventarioService.updateProducto(id, producto);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        try {
            productoInventarioService.deleteProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}