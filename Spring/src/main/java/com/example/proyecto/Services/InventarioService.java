/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.Services;

import com.example.proyecto.LogicaDeNegocio.Inventario;
import com.example.proyecto.LogicaDeNegocio.Producto;
import com.example.proyecto.Persistencia.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> getAllInventarios() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> getInventarioById(Long id) {
        return inventarioRepository.findById(id);
    }

    public Inventario saveInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public String deleteInventario(Long id) {
        if (inventarioRepository.existsById(id)) {
            inventarioRepository.deleteById(id);
            return "Inventario eliminado correctamente.";
        }
        return "Inventario no encontrado.";
    }

    public String updateInventario(Long id, Inventario inventarioActualizado) {
        return inventarioRepository.findById(id).map(inventario -> {
            inventario.setProductos(inventarioActualizado.getProductos());
            inventarioRepository.save(inventario);
            return "Inventario actualizado correctamente.";
        }).orElse("Inventario no encontrado.");
    }

    // Métodos adicionales para manejar productos
    public void agregarProducto(Long inventarioId, Producto producto) {
        Inventario inventario = inventarioRepository.findById(inventarioId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        inventario.agregarProducto(producto);
        inventarioRepository.save(inventario);
    }

    public void eliminarProducto(Long inventarioId, Long productoId) {
    Inventario inventario = inventarioRepository.findById(inventarioId)
            .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    inventario.getProductos().removeIf(producto -> producto.getId().equals(productoId));
    inventarioRepository.save(inventario);
}
}