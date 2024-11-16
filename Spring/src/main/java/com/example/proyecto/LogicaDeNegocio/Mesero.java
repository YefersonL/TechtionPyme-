package com.example.proyecto.LogicaDeNegocio;

import com.example.proyecto.LogicaDeNegocio.Empleado;
import jakarta.persistence.*;
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

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