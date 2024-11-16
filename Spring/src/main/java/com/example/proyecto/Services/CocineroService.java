/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.Services;

import com.example.proyecto.LogicaDeNegocio.Cocinero;
import com.example.proyecto.LogicaDeNegocio.Pedido;
import com.example.proyecto.Persistencia.CocineroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocineroService {

    @Autowired
    private CocineroRepository cocineroRepository;

    public List<Pedido> obtenerPedidosDeCocinero(Long cocineroId) {
        Cocinero cocinero = cocineroRepository.findById(cocineroId)
                .orElseThrow(() -> new RuntimeException("Cocinero no encontrado"));
        return cocinero.getPedidos();
    }
}
