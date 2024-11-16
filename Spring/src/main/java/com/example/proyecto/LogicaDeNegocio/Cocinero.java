
package com.example.proyecto.LogicaDeNegocio;

<<<<<<< HEAD
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
    
=======
import jakarta.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Cocinero")
public class Cocinero extends Empleado {

>>>>>>> 103b3757c2bab874c66fd8d92c42dd720bfd5722
    @Column(name = "especialidad", nullable = true)
    private String especialidad;

<<<<<<< HEAD

    public void visualizarPedidos(){
        
    }
    
    public void actualizacionDePedido(){
        
=======
    @OneToMany(mappedBy = "cocinero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos; // Relación con pedidos asignados al cocinero

    public Cocinero() {
    }

    public Cocinero(String nombre, int identificacion, double salarioBase, String especialidad) {
        super(nombre, identificacion, salarioBase);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
>>>>>>> 103b3757c2bab874c66fd8d92c42dd720bfd5722
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public void realizarTarea() {
        // Implementación específica para el cocinero
    }

    public void visualizarPedidos() {
        // Lógica para visualizar pedidos
    }

    public void actualizacionDePedido() {
        // Lógica para actualizar pedidos
    }
}
