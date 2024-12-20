
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Table (name = "Empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Campo ID
    
    private String nombre;
    private int identificacion;
    private double salarioBase;




    public abstract void realizarTarea();


   
   
   
    
}
