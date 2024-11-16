
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
        
    }


    @Override
    public void realizarTarea() {
      
    }
    
    
    
    
    
}
