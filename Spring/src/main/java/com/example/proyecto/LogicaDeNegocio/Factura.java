
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private double montoTotal;




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