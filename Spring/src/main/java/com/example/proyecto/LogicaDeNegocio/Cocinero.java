
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

    
    public void actualizacionDePedido(){

    }
        

    @OneToMany(mappedBy = "cocinero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos; // Relación con pedidos asignados al cocinero


    public void visualizarPedidos() {
        // Lógica para visualizar pedidos
    }



    public void  realizarTarea() {
        return;
    }
}
