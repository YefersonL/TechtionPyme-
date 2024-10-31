
package com.example.proyecto.Persistencia;

import com.example.proyecto.LogicaDeNegocio.Rol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<Rol, Long> {
     Rol findByNombre(String nombre);
}
