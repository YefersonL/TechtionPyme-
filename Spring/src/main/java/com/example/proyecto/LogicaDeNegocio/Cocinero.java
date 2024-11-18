
package com.example.proyecto.LogicaDeNegocio;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@DiscriminatorValue("cocinero")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cocinero extends Empleado{

    @Column(name = "especialidad", nullable = true)
    private String especialidad;


    public void visualizarPedidos(){
        
    }
    
    public void actualizacionDePedido(){
        

    @OneToMany(mappedBy = "cocinero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos; // Relación con pedidos asignados al cocinero


        public void realizarTarea() {
        // Implementación específica para el cocinero
    }

    public void visualizarPedidos() {
        // Lógica para visualizar pedidos
    }

    public void actualizacionDePedido() {
        // Lógica para actualizar pedidos
    }
}
