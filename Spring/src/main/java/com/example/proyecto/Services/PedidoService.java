/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.Services;

import com.example.proyecto.LogicaDeNegocio.Pedido;
import com.example.proyecto.Persistencia.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    private Queue<Pedido> colaPedidos = new LinkedList<>(); // Pedidos en espera
    private Stack<Pedido> pilaPedidosListos = new Stack<>(); // Pedidos listos para entrega

    public Pedido crearPedido(Pedido pedido) {
        colaPedidos.add(pedido); // Agregar a la cola de espera
        return pedidoRepository.save(pedido);
    }

    public Pedido asignarCocinero(Pedido pedido, Long cocineroId) {
        pedido.setEstado("LISTO");
        pilaPedidosListos.push(pedido); // Mover a la pila de listos
        return pedidoRepository.save(pedido);
    }

    public Pedido entregarPedido() {
        if (!pilaPedidosListos.isEmpty()) {
            Pedido pedido = pilaPedidosListos.pop();
            pedido.setEstado("ENTREGADO");
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    public Queue<Pedido> obtenerPedidosEnEspera() {
        return colaPedidos;
    }

    public Stack<Pedido> obtenerPedidosListos() {
        return pilaPedidosListos;
    }
}