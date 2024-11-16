
package com.example.proyecto.Persistencia;

import com.example.proyecto.LogicaDeNegocio.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}