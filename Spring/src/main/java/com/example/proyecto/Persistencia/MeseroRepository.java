package com.example.proyecto.Persistencia;


import com.example.proyecto.LogicaDeNegocio.Mesero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeseroRepository extends JpaRepository<Mesero, Long> {
    
}