
package com.example.proyecto.Persistencia;

import com.example.proyecto.LogicaDeNegocio.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}