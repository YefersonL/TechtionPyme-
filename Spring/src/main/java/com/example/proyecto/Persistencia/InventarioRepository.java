
package com.example.proyecto.Persistencia;

import com.example.proyecto.LogicaDeNegocio.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}