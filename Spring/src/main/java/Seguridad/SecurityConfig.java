package Seguridad;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF si es necesario solo en entornos de prueba
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/Empleados/administrador").hasAuthority("administrador") // Solo los Administradores pueden acceder a /empleados
                .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
            )
            .formLogin(form -> form   // Habilita la autenticación con formulario de inicio de sesión
                .loginPage("/login")  // Ruta de la página de inicio de sesión personalizada, si la tienes
                .permitAll()          // Permitir el acceso a todos al formulario de inicio de sesión
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("administrador")
            .password(passwordEncoder().encode("adminpass"))
            .roles("administrador")
            .build());
        return manager;
  }

}

    

