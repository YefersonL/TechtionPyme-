/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.Controladores;

import com.example.proyecto.LogicaDeNegocio.Pedido;
import com.example.proyecto.Services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;
import java.util.Stack;

@RestController
@RequestMapping("/pedidos")
public class PedidoControlador {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        return ResponseEntity.ok(nuevoPedido);
    }

    @PutMapping("/{id}/asignar-cocinero/{cocineroId}")
    public ResponseEntity<Pedido> asignarCocinero(@PathVariable Long id, @PathVariable Long cocineroId) {
        Pedido pedido = pedidoService.asignarCocinero(pedidoService.obtenerPedidosEnEspera().poll(), cocineroId);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/entregar")
    public ResponseEntity<Pedido> entregarPedido() {
        Pedido pedidoEntregado = pedidoService.entregarPedido();
        if (pedidoEntregado != null) {
            return ResponseEntity.ok(pedidoEntregado);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/en-espera")
    public ResponseEntity<Queue<Pedido>> obtenerPedidosEnEspera() {
        return ResponseEntity.ok(pedidoService.obtenerPedidosEnEspera());
    }

    @GetMapping("/listos")
    public ResponseEntity<Stack<Pedido>> obtenerPedidosListos() {
        return ResponseEntity.ok(pedidoService.obtenerPedidosListos());
    }
}