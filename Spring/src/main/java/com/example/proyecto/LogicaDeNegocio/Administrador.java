
package com.example.proyecto.LogicaDeNegocio;


import com.example.proyecto.LogicaDeNegocio.Empleado;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("administrador")
public class Administrador extends Empleado{

    

    public Administrador() {
        super();
    }

    @Override
    public void realizarTarea() {
       
    }
    
    
    
}
