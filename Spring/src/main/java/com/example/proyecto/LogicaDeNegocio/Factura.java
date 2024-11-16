
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private double montoTotal;

    // Constructores
    public Factura(LocalDateTime fecha, double montoTotal) {
        this.fecha = fecha;
        this.montoTotal = montoTotal;
    }

    public Factura() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    // Método para calcular el total (puedes añadir lógica específica aquí)
    public void calcularTotal(double... items) {
        this.montoTotal = 0;
        for (double item : items) {
            this.montoTotal += item;
        }
    }

    // Método para generar un código único basado en fecha e ID
    public String codigoFactura() {
        return "FA-" + (id != null ? id : "TEMP") + "-" + fecha.toLocalDate().toString();
    }
}