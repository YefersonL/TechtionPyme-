package com.example.proyecto.Controladores;

import com.example.proyecto.LogicaDeNegocio.Empleado;
import com.example.proyecto.Services.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    public String login(@RequestParam String nombre, @RequestParam String password) {
        Empleado empleado = empleadoService.autenticarEmpleado(nombre, password);
        if (empleado != null) {
            return "Inicio de sesión exitoso: " + empleado.getNombre();
        } else {
            return "Credenciales incorrectas";
        }
    }
}