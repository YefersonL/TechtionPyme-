
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;



@Entity
@DiscriminatorValue("cocinero")
public class Cocinero extends Empleado{
    
    @Column(name = "especialidad", nullable = true)
    private String especialidad;

    public Cocinero(String especialidad, String nombre, int identificacion, double salarioBase, String contraseña) {
        super(nombre, identificacion, salarioBase, contraseña);
        this.especialidad = especialidad;
    }

    public Cocinero(String especialidad) {
        this.especialidad = especialidad;
    }
    

    
    public Cocinero() {
        super();
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    public void visualizarPedidos(){
        
    }
    
    public void actualizacionDePedido(){
        
    }


    @Override
    public void realizarTarea() {
      
    }
    
    
    
    
    
}
