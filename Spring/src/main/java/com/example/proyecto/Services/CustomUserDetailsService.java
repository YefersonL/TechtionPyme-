package com.example.proyecto.Services;

import com.example.proyecto.LogicaDeNegocio.Empleado;
import com.example.proyecto.Persistencia.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca al empleado por nombre
        Empleado empleado = empleadoRepository.findByNombre(username);
        if (empleado == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // Asigna roles según el tipo de empleado
        String[] roles = { "USER" }; // Cambia esto según tu lógica de roles
        if ("administrador".equalsIgnoreCase(empleado.getTipo())) {
            roles = new String[] { "ADMIN" };
        }

        // Devuelve el objeto UserDetails con el nombre de usuario, contraseña y roles
        return org.springframework.security.core.userdetails.User
                .withUsername(empleado.getNombre())
                .password(empleado.getPassword())
                .roles(roles) // Asignar roles
                .build();
    }
}