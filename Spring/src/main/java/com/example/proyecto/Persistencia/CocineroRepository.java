
package com.example.proyecto.Persistencia;

import com.example.proyecto.LogicaDeNegocio.Cocinero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocineroRepository extends JpaRepository<Cocinero, Long> {
    
}