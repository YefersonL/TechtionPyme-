package com.example.proyecto.LogicaDeNegocio;

import com.example.proyecto.LogicaDeNegocio.Empleado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Mesero")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mesero extends Empleado {  
    
    @Column(name = "turno", nullable = true)
    private String turno;
    
  

    




   

    @Override
    public void realizarTarea() {
        // Implementación específica para mesero
    }
    
    public void tomarOrden(){
        
    }
    
    public void enviarOrdenAcocina(){
        
    }
    
    
    
    
}