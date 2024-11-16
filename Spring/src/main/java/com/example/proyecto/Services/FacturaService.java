/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.Services;

import com.example.proyecto.LogicaDeNegocio.Factura;
import com.example.proyecto.Persistencia.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    // Crear factura
    public Factura createFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    // Obtener todas las facturas
    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    // Obtener factura por ID
    public Optional<Factura> getFacturaById(Long id) {
        return facturaRepository.findById(id);
    }

    // Actualizar factura
    public Factura updateFactura(Long id, Factura facturaActualizada) {
        return facturaRepository.findById(id).map(factura -> {
            factura.setFecha(facturaActualizada.getFecha());
            factura.setMontoTotal(facturaActualizada.getMontoTotal());
            return facturaRepository.save(factura);
        }).orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    }

    // Eliminar factura
    public void deleteFactura(Long id) {
        if (!facturaRepository.existsById(id)) {
            throw new RuntimeException("Factura no encontrada");
        }
        facturaRepository.deleteById(id);
    }
}