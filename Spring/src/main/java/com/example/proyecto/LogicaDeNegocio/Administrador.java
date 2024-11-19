/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("Administrador")
public class Administrador extends Empleado {

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