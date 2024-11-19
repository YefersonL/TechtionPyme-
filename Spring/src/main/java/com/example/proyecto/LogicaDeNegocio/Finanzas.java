/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Finanzas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Factura> facturas;

    @OneToMany
    private List<ProductoInventario> productos;

    @Column
    private double pasivo;


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