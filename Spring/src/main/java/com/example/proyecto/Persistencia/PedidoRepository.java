
package com.example.proyecto.Persistencia;

import com.example.proyecto.LogicaDeNegocio.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEstado(String estado); 
}
