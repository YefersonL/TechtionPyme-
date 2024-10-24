
package com.example.proyecto.Persistencia;



import com.example.proyecto.LogicaDeNegocio.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

}

