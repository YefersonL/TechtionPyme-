
package com.example.proyecto.Services;


import com.example.proyecto.LogicaDeNegocio.Empleado;
import com.example.proyecto.LogicaDeNegocio.Rol;
import com.example.proyecto.Persistencia.EmpleadoRepository;
import com.example.proyecto.Persistencia.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private RolRepository rolRepository;

    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // Busca al empleado por su nombre
    Rol rol = rolRepository.findByNombre(username);

    // Verifica si el empleado fue encontrado
    if (rol == null) {
        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }

    // Verifica si el empleado tiene un rol de administrador
    if (rol.getNombre()== null || !rol.getNombre().equals("ADMIN")) {
        throw new UsernameNotFoundException("Usuario no autorizado: " + username);
    }

    // Retorna un objeto UserDetails para el empleado autenticado
    return org.springframework.security.core.userdetails.User.withUsername(rol.getNombre())
            .authorities("administrador") // Agregar la autoridad del rol
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build();
}

}
