
package com.example.proyecto.Controladores;

import com.example.proyecto.Controladores.EmpleadoControlador;

import com.example.proyecto.LogicaDeNegocio.Pedido;
import com.example.proyecto.Services.MeseroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meseros")
public class MeseroControlador extends EmpleadoControlador {

    @Autowired
    private MeseroService meseroService;

    @GetMapping("/{id}/pedidos")
    public ResponseEntity<List<Pedido>> obtenerPedidos(@PathVariable Long id) {
        List<Pedido> pedidos = meseroService.obtenerPedidosDeMesero(id);
        return ResponseEntity.ok(pedidos);
    }
}