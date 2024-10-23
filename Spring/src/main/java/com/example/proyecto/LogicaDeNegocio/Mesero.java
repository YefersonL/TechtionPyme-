package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Mesero")
public class Mesero extends Empleado {  
    
    @Column(name = "salario_base", nullable = false)
    private double salarioBase;
 
    public Mesero(String nombre, int identificacion, double salarioBase) {
        super(nombre, identificacion);
        this.salarioBase = salarioBase;
    }
    
    public Mesero() {
        super();
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }


    public void realizarTarea() {
        // Implementación específica para mesero
    }
}