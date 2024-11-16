package com.example.proyecto.Controladores;

import com.example.proyecto.LogicaDeNegocio.Empleado;
import com.example.proyecto.Services.EmpleadoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoControlador {
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Map<String, String>> createEmpleado(@RequestBody Map<String, Object> empleadoData) {
        // Llamada al servicio para crear el empleado
        Map<String, String> response = (Map<String, String>) empleadoService.createEmpleado(empleadoData);

        // Verifica si la creación fue exitosa o si hubo algún error
        if (response.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // Si hay error
        }

        // Si la creación es exitosa, responde con una creación exitosa
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.getAllEmpleados();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> updateEmpleado(@PathVariable Long id, @RequestBody Map<String, Object> empleadoData) {
        Map<String, String> response = empleadoService.updateEmpleado(id, empleadoData);
        if (response.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> deleteEmpleado(@PathVariable Long id) {
        Map<String, String> response = empleadoService.deleteEmpleado(id);
        if (response.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
}