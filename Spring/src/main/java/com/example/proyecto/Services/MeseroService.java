/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.Services;

import com.example.proyecto.LogicaDeNegocio.Mesero;
import com.example.proyecto.LogicaDeNegocio.Pedido;
import com.example.proyecto.Persistencia.MeseroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeseroService {

    @Autowired
    private MeseroRepository meseroRepository;

    public List<Pedido> obtenerPedidosDeMesero(Long meseroId) {
        Mesero mesero = meseroRepository.findById(meseroId)
                .orElseThrow(() -> new RuntimeException("Mesero no encontrado"));
        return mesero.getPedidos();
    }
}