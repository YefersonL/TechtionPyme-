
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Cocinero")
public class Cocinero extends Empleado {

    @Column(name = "especialidad", nullable = true)
    private String especialidad;

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
