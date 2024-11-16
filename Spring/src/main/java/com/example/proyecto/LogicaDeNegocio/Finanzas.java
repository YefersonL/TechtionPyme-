/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Finanzas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Factura> facturas;

    @OneToMany
    private List<Producto> productos;

    @Column
    private double pasivo;

    public Finanzas(List<Factura> facturas, List<Producto> productos, double pasivo) {
        this.facturas = facturas;
        this.productos = productos;
        this.pasivo = pasivo;
    }

    public Finanzas() {
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getPasivo() {
        return pasivo;
    }

    public void setPasivo(double pasivo) {
        this.pasivo = pasivo;
    }

    public void generarBalance() {
        // Lógica para generar balance de finanzas
    }

    public void cierreMensual() {
        // Lógica para el cierre mensual
    }

    public void chatbotAyudaFinanzas() {
        // Lógica para el chatbot de ayuda en finanzas
    }
}