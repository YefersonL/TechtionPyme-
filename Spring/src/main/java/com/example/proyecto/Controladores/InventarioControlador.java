/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.Controladores;

import com.example.proyecto.LogicaDeNegocio.Inventario;
import com.example.proyecto.Services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventarios")
public class InventarioControlador {

    @Autowired
    private InventarioService inventarioService;

    // Crear un nuevo inventario
    @PostMapping
    public ResponseEntity<Inventario> createInventario(@RequestBody Inventario inventario) {
        try {
            Inventario nuevoInventario = inventarioService.createInventario(inventario);
            return new ResponseEntity<>(nuevoInventario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Obtener todos los inventarios
    @GetMapping
    public ResponseEntity<List<Inventario>> getAllInventarios() {
        List<Inventario> inventarios = inventarioService.getAllInventarios();
        return new ResponseEntity<>(inventarios, HttpStatus.OK);
    }

    // Actualizar un inventario existente
    @PutMapping("/{id}")
    public ResponseEntity<Inventario> updateInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        try {
            Inventario inventarioActualizado = inventarioService.updateInventario(id, inventario);
            return new ResponseEntity<>(inventarioActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un inventario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id) {
        try {
            inventarioService.deleteInventario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}