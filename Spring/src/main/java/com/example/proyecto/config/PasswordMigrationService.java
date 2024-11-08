/*
package com.example.proyecto.config;

import com.example.proyecto.LogicaDeNegocio.User;
import com.example.proyecto.Persistencia.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PasswordMigrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswordMigrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public void migratePasswords() {
        // Recupera todos los usuarios
        List<User> users = (List<User>) userRepository.findAll();

        for (User user : users) {
            String password = user.getPassword();
            // Verifica si la contraseña ya está codificada en BCrypt
            if (!password.startsWith("$2a$")) {  // Prefijo de BCrypt
                // Codifica y guarda la contraseña
                String encodedPassword = passwordEncoder.encode(password);
                user.setPassword(encodedPassword);
                userRepository.save(user);
            }
        }
    }
}
*/