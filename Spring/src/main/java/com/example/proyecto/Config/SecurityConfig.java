package com.example.proyecto.Config;

import com.example.proyecto.Services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    
    
     @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF si es necesario solo en entornos de prueba
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login").permitAll() // Permitir acceso a la página de inicio de sesión
                .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
            )
            .formLogin(form -> form   // Habilita la autenticación con formulario de inicio de sesión
                .loginPage("/login")  // Ruta de la página de inicio de sesión personalizada
                .usernameParameter("nombre") // Cambia el nombre del parámetro de usuario a "nombre"
                .passwordParameter("password") // Asegúrate de que el nombre del parámetro de contraseña sea "password"
                .defaultSuccessUrl("/empleados", true) // Redirigir a la página de inicio después de un inicio de sesión exitoso
                .failureUrl("/login?error=true") // Redirigir a la página de inicio de sesión con un error si la autenticación falla
                .permitAll()          // Permitir el acceso a todos al formulario de inicio de sesión
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // URL para cerrar sesión
                .logoutSuccessUrl("/login?logout=true") // Redirigir a la página de inicio de sesión después de cerrar sesión
                .permitAll() // Permitir el acceso a todos al cierre de sesión
            );
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // Usa el servicio inyectado
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}