
package com.example.proyecto.Persistencia;

import com.example.proyecto.LogicaDeNegocio.ProductoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoInventario, Long> {
    
}