package com.example.proyecto.Persistencia;


import com.example.proyecto.LogicaDeNegocio.Empleado;
import com.example.proyecto.Persistencia.EmpleadoRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface MeseroRepository extends EmpleadoRepository {
   
    // Aquí puedes agregar métodos específicos para manejar meseros si es necesario
}
