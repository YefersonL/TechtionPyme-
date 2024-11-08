package com.example.proyecto.Services;

import com.example.proyecto.LogicaDeNegocio.Empleado;
import com.example.proyecto.Persistencia.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Instanciar BCryptPasswordEncoder

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        System.out.println("Usuario recibido: " + nombre + "-");

        // Busca al empleado por nombre
        Empleado empleado = empleadoRepository.findByNombre(nombre);

        if (empleado == null) {
            System.out.println("Empleado no encontrado en la base de datos para el nombre: " + nombre);
            throw new UsernameNotFoundException("Usuario no encontrado: " + nombre);
        }

        // Aquí deberías obtener la contraseña proporcionada por el usuario desde el formulario
        // NOTA: No puedes obtener la contraseña de la base de datos porque ya está encriptada
        // En su lugar, Spring Security se encargará de comparar la contraseña proporcionada con la que está en la base de datos.
        // Asigna roles según el tipo de empleado
        String[] roles = {"USER"}; // Cambia esto según tu lógica de roles
        if ("administrador".equalsIgnoreCase(empleado.getTipo())) {
            roles = new String[]{"administrador"};
        }

        // Devuelve el objeto UserDetails con el nombre de usuario, contraseña y roles
        return org.springframework.security.core.userdetails.User
                .withUsername(empleado.getNombre())
                .password(empleado.getPassword()) // La contraseña cifrada en la base de datos
                .roles(roles) // Asignar roles
                .build();
    }

}
