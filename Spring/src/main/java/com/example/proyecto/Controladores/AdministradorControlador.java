
package com.example.proyecto.Controladores;


import com.example.proyecto.LogicaDeNegocio.Administrador;
import com.example.proyecto.Services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/administradores")
public class AdministradorControlador {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createAdministrador(@RequestBody Map<String, Object> administradorData) {
        return administradorService.createAdministrador(administradorData);
    }

    @GetMapping
    public List<Administrador> getAllAdministradores() {
        return administradorService.getAllAdministradores();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateAdministrador(@PathVariable Long id, @RequestBody Map<String, Object> administradorData) {
        Map<String, String> response = administradorService.updateAdministrador(id, administradorData);
        if (response.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAdministrador(@PathVariable Long id) {
        Map<String, String> response = administradorService.deleteAdministrador(id);
        if (response.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
