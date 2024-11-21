package com.example.proyecto.Controladores;

import com.example.proyecto.LogicaDeNegocio.Empleado;
import com.example.proyecto.Services.EmpleadoService;

import java.util.HashMap;
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
    @CrossOrigin(origins = "http://127.0.0.1:8080")
    @PreAuthorize("hasAuthority('ADMIN')") // Asegura que solo los usuarios con el rol ADMIN puedan crear un empleado
    public ResponseEntity<Map<String, String>> createEmpleado(@RequestBody Map<String, Object> empleadoData) {
        try {
            // Llamada al servicio para crear el empleado
            Object result = empleadoService.createEmpleado(empleadoData);

            // Verifica si el resultado es del tipo esperado
            if (result instanceof Map) {
                Map<String, String> response = (Map<String, String>) result;

                // Verifica si la creación fue exitosa o si hubo algún error
                if (response.containsKey("error")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // Si hay error
                }

                // Si la creación es exitosa, responde con una creación exitosa
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                // Si el resultado no es el tipo esperado, se maneja el error de manera adecuada
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Unexpected response type from the service");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
            }
        } catch (Exception e) {
            // Captura cualquier excepción no controlada y devuelve un mensaje de error
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "An unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
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