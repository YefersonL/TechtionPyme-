/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.Controladores;

import com.example.proyecto.LogicaDeNegocio.Factura;
import com.example.proyecto.Services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/facturas")
@CrossOrigin(origins = "http://127.0.0.1:8080")
public class FacturaControlador {

    @Autowired
    private FacturaService facturaService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createFactura(@RequestBody Map<String, Object> facturaData) {
        try {
            // Convertir la fecha desde el Map
            String fechaStr = (String) facturaData.get("fecha");
            LocalDateTime fecha = LocalDateTime.parse(fechaStr);

            // Convertir montoTotal desde el Map
            double montoTotal = Double.parseDouble(facturaData.get("montoTotal").toString());

            // Crear una nueva factura
            Factura factura = new Factura();
            factura.setFecha(fecha);
            factura.setMontoTotal(montoTotal);

            // Guardar la factura en la base de datos (esto dependerá de tu lógica de servicio)
            facturaService.saveFactura(factura);

            // Responder con un mensaje de éxito
            Map<String, String> response = new HashMap<>();
            response.put("message", "Factura creada exitosamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Manejo de excepciones
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al crear la factura: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Obtener todas las facturas
    @GetMapping
    public ResponseEntity<List<Factura>> getAllFacturas() {
        List<Factura> facturas = facturaService.getAllFacturas();
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }

    // Obtener factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long id) {
        return facturaService.getFacturaById(id)
                .map(factura -> new ResponseEntity<>(factura, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar una factura
    @PutMapping("/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Long id, @RequestBody Factura facturaActualizada) {
        try {
            Factura factura = facturaService.updateFactura(id, facturaActualizada);
            return new ResponseEntity<>(factura, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una factura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Long id) {
        try {
            facturaService.deleteFactura(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}