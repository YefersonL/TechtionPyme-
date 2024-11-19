package com.example.proyecto.LogicaDeNegocio;

import com.example.proyecto.LogicaDeNegocio.Empleado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("Mesero")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mesero extends Empleado {  
    
    @Column(name = "turno", nullable = true)
    private String turno;


    @OneToMany(mappedBy = "mesero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos; // Relación con los pedidos asignados al mesero

    @Override
    public void realizarTarea() {
        // Implementación específica para el mesero
    }

    public void tomarOrden() {
        // Lógica para tomar una orden
    }

    public void enviarOrdenAcocina() {
        // Lógica para enviar la orden a la cocina
    }
}