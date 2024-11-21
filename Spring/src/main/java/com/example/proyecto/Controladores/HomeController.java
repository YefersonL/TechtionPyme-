package com.example.proyecto.Controladores;

import com.example.proyecto.LogicaDeNegocio.User;
import com.example.proyecto.Persistencia.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;  // Codificador de contraseñas, ya definido en SecurityConfig

    /**
     * Endpoint para mostrar el home privado
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/index.html"; // Redirige a index.html en static
    }

    /**
     * Endpoint para iniciar sesión
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return Respuesta con token y redirección, o un error
     */
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        // Busca el usuario por su nombre
        User user = userRepository.findUserByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            if ("ADMIN".equals(user.getRole())) {
                // Genera un token (puedes usar JWT en producción)
                String token = generateToken(user);

                // Respuesta con token y redirección
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("role", user.getRole());
                response.put("continueUrl", "/admin_dashboard.html");

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos de administrador.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas.");
        }
    }

    @GetMapping("/admin_dashboard.html")
    public String showAdminDashboard() {
        return "redirect:/admin_dashboard.html";  // Redirige a /admin_dashboard.html en static
    }

    /**
     * Método para registrar empleados, accesible solo por administradores
     * @param continueUrl URL opcional para redirección
     * @return Redirección a la URL proporcionada o a la predeterminada
     */
    @PostMapping("/crear")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> register(@RequestParam(required = false) String continueUrl) {
        // Verifica que la URL de continuación no esté vacía o nula
        URI uri = URI.create(continueUrl != null && !continueUrl.isEmpty() ? continueUrl : "/empleados/create");
        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
    }

    /**
     * Método simulado para generar un token
     * @param user Usuario para el que se genera el token
     * @return Token generado
     */
    private String generateToken(User user) {
        // Genera un token simulado (usa JWT en un proyecto real)
        return "token_" + user.getUsername() + "_secure";
    }
}
