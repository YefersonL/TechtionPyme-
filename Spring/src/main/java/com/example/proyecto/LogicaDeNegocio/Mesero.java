package com.example.proyecto.LogicaDeNegocio;

import com.example.proyecto.LogicaDeNegocio.Empleado;
import jakarta.persistence.*;
<<<<<<< HEAD
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
    
  

    
=======
import java.util.List;

@Entity
@DiscriminatorValue("Mesero")
public class Mesero extends Empleado {

    @Column(name = "turno", nullable = true)
    private String turno;

    @OneToMany(mappedBy = "mesero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos; // Relación con los pedidos asignados al mesero

    public Mesero() {
    }

    public Mesero(String nombre, int identificacion, double salarioBase, String turno) {
        super(nombre, identificacion, salarioBase);
        this.turno = turno;
    }
>>>>>>> 103b3757c2bab874c66fd8d92c42dd720bfd5722




    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public void realizarTarea() {
        // Implementación específica para el mesero
    }

    public void tomarOrden() {
        // Lógica para tomar una orden
    }

    public void enviarOrdenAcocina() {
        // Lógica para enviar la orden a la cocina
    }
}