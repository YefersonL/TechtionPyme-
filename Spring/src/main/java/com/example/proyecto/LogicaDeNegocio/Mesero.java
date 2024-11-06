package com.example.proyecto.LogicaDeNegocio;

import com.example.proyecto.LogicaDeNegocio.Empleado;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Mesero")
public class Mesero extends Empleado {  
    
    @Column(name = "turno", nullable = true)
    private String turno;

    

    public Mesero(String turno) {
        this.turno = turno;
    }
    
  
    
    
    public Mesero() {
        super();
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

   

    @Override
    public void realizarTarea() {
        // Implementación específica para mesero
    }
    
    public void tomarOrden(){
        
    }
    
    public void enviarOrdenAcocina(){
        
    }
    
    
    
    
}