/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("Administrador")
public class Administrador extends Empleado {
    
    @Column(name = "departamento", nullable = true)
    private String departamento;

    public Administrador(String nombre, int identificacion, double salarioBase, String departamento) {
        super(nombre, identificacion, salarioBase);
        this.departamento = departamento;
    }

    public Administrador() {
        super();
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void modificarCuentas() {
        // Lógica para modificar cuentas
    }

    public void gestionarPedidos(List<Pedido> pedidos) {
        // Lógica para gestionar pedidos
    }

    public void revisarFacturas(List<Factura> facturas) {
        // Lógica para revisar facturas
    }

    @Override
    public void realizarTarea() {
        // Implementación específica para el administrador
    }
}