
package com.example.proyecto.Controladores;


import com.example.proyecto.LogicaDeNegocio.Pedido;
import com.example.proyecto.Services.CocineroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cocineros")
public class CocineroControlador extends EmpleadoControlador {

    @Autowired
    private CocineroService cocineroService;

    @GetMapping("/{id}/pedidos")
    public ResponseEntity<List<Pedido>> obtenerPedidos(@PathVariable Long id) {
        List<Pedido> pedidos = cocineroService.obtenerPedidosDeCocinero(id);
        return ResponseEntity.ok(pedidos);
    }
}

