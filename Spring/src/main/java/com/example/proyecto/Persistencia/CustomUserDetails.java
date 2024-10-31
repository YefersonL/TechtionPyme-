
package com.example.proyecto.Persistencia;

import com.example.proyecto.LogicaDeNegocio.Empleado;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    
    private final Empleado empleado;

    public CustomUserDetails(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aquí puedes asignar roles a tu empleado
        return null; // Devuelve los roles adecuados
    }

    @Override
    public String getPassword() {
        return empleado.getContraseña(); // Devuelve la contraseña del empleado
    }

    public String getnombre() {
        return empleado.getNombre(); // Devuelve el nombre de usuario
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Cambia según tu lógica de negocio
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Cambia según tu lógica de negocio
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Cambia según tu lógica de negocio
    }

    @Override
    public boolean isEnabled() {
        return true; // Cambia según tu lógica de negocio
    }

    @Override
    public String getUsername() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
