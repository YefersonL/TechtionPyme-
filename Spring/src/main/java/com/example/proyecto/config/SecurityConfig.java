package com.example.proyecto.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())  // Deshabilita CSRF, útil si estás usando RESTful APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()  // Rutas públicas sin autenticación
                        .requestMatchers("/login").permitAll() // Ruta pública para login
                        .requestMatchers("/create/empleados").hasAuthority("ADMIN") // Solo ADMIN puede acceder al registro de empleados
                        .requestMatchers("/static/**", "/resources/**").permitAll()  // Rutas estáticas (CSS, JS, imágenes)
                        .anyRequest().authenticated()  // Todas las demás rutas requieren autenticación
                )
                .formLogin()
                .loginPage("/login")  // Ruta personalizada de login
                .loginProcessingUrl("/login")  // Ruta que manejará la autenticación
                .usernameParameter("username")  // Parámetro del formulario para el nombre de usuario
                .passwordParameter("password")  // Parámetro del formulario para la contraseña
                .defaultSuccessUrl("/home", true)  // Redirige a /home después de un login exitoso
                .failureUrl("/login?error=true")  // Redirige a login si el login falla
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Utiliza BCrypt para cifrar las contraseñas
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));  // Origen permitido (puedes ajustarlo a tu frontend)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));  // Métodos HTTP permitidos
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));  // Encabezados permitidos
        configuration.setAllowCredentials(true);  // Permitir credenciales (cookies, autenticación)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Aplica la configuración a todas las rutas
        return source;
    }
}
