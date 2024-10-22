
package com.example.proyecto.LogicaDeNegocio;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@RestController
@RequestMapping("/Empleados")
public class EmpleadoControlador {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Obtener todos los usuarios
    @GetMapping
    public List<SecurityProperties.User> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@RequestBody Empleado empleado) {
        Map<String, String> response = new HashMap<>();
        try {
            empleadoRepository.save(empleado);
            response.put("message", "Usuario agregado correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("error", "Error al agregar usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
    
    
    
    
}
