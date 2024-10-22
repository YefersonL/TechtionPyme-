
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table (name = "Empleados")
public abstract class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private String nombre;
    private int identificacion;

    public Empleado(String nombre, int id) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }
    
    public Empleado() {
       
    }
    
   // public abstract realizarTarea();
    
   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }
   
   
   
    
}
