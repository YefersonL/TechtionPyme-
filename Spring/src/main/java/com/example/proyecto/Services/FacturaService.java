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
    public Factura saveFactura(Factura factura) {
        // Si la factura tiene un id, es una actualización, si no, es una creación
        if (factura.getId() != null && facturaRepository.existsById(factura.getId())) {
            // Factura ya existe, por lo que vamos a actualizarla
            Factura facturaExistente = facturaRepository.findById(factura.getId()).get();
            facturaExistente.setFecha(factura.getFecha());
            facturaExistente.setMontoTotal(factura.getMontoTotal());
            return facturaRepository.save(facturaExistente);
        } else {
            // Si no tiene ID, es una nueva factura
            return facturaRepository.save(factura);
        }
    }

    // Eliminar factura
    public void deleteFactura(Long id) {
        if (!facturaRepository.existsById(id)) {
            throw new RuntimeException("Factura no encontrada");
        }
        facturaRepository.deleteById(id);
    }
}