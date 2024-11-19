package com.example.proyecto.Controladores;

import com.example.proyecto.LogicaDeNegocio.User;
import com.example.proyecto.Persistencia.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "private home";
    }



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inyecta el PasswordEncoder


    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        // Busca el usuario por el nombre de usuario
        User user = userRepository.findUserByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Verifica que el rol sea ADMIN
            if ("ADMIN".equals(user.getRole())) {
                // Genera un token (en producción usarías una librería como JWT)
                String token = generateToken(user);  // Método para generar un token (JWT o similar)

                // Prepara la respuesta
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("role", user.getRole());  // Incluye el rol
                response.put("continueUrl", "/register/empleados/create");

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos de administrador.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas.");
        }
    }

    // Método simulado para generar un token
    private String generateToken(User user) {
        // Aquí podrías generar un JWT o algún otro tipo de token
        return "tokenGenerado";  // Debería ser un token real
    }

    @PostMapping("/register/empleados/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> register(@RequestParam(required = false) String continueUrl) {
        // Verifica si la solicitud es de tipo OPTIONS (preflight)
        if ("OPTIONS".equalsIgnoreCase(RequestContextHolder.currentRequestAttributes().getAttribute("javax.servlet.request.method", RequestAttributes.SCOPE_REQUEST).toString())) {
            // No redirigir en preflight
            return ResponseEntity.ok().build();
        }

        // Si el parámetro 'continueUrl' está presente, redirige a esa URL
        if (continueUrl != null && !continueUrl.isEmpty()) {
            URI uri = URI.create(continueUrl);
            return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
        }

        // Si no se proporciona 'continueUrl', redirige a la URL predeterminada
        URI uri = URI.create("http://localhost:3000/v1/register/empleados/create");
        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
    }


}
