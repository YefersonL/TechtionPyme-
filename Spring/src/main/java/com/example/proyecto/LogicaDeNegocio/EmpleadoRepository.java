
package com.example.proyecto.LogicaDeNegocio;


import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<User, Long> {

    public void save(Empleado empleado);
}

