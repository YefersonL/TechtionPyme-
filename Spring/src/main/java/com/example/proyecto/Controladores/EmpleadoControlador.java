package com.example.proyecto.Controladores;

import com.example.proyecto.LogicaDeNegocio.Empleado;
import com.example.proyecto.Services.EmpleadoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/empleados")
public class EmpleadoControlador {
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createEmpleado(@RequestBody Map<String, Object> empleadoData) {
        System.out.println("Conectado");
        return empleadoService.createEmpleado(empleadoData);
    }

    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.getAllEmpleados();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateEmpleado(@PathVariable Long id, @RequestBody Map<String, Object> empleadoData) {
        Map<String, String> response = empleadoService.updateEmpleado(id, empleadoData);
        if (response.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmpleado(@PathVariable Long id) {
        Map<String, String> response = empleadoService.deleteEmpleado(id);
        if (response.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
