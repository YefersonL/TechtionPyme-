package com.example.proyecto.Services;

import com.example.proyecto.LogicaDeNegocio.Administrador;
import com.example.proyecto.LogicaDeNegocio.Factura;
import com.example.proyecto.LogicaDeNegocio.Pedido;
import com.example.proyecto.Persistencia.AdministradorRepository;
import com.example.proyecto.Persistencia.FacturaRepository;
import com.example.proyecto.Persistencia.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    // Obtener un administrador por ID
    public Administrador obtenerAdministradorPorId(Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Administrador no encontrado con el ID: " + id));
    }

    // Modificar una cuenta 
    public Administrador modificarCuenta(Long id, Administrador datosActualizados) {
        Administrador administrador = obtenerAdministradorPorId(id);
        administrador.setNombre(datosActualizados.getNombre());
        administrador.setIdentificacion(datosActualizados.getIdentificacion());
        administrador.setSalarioBase(datosActualizados.getSalarioBase());
        return administradorRepository.save(administrador);
    }

    // Gestionar pedidos: Obtener todos los pedidos
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // Revisar todas las facturas
    public List<Factura> listarFacturas() {
        return facturaRepository.findAll();
    }

    // Revisar una factura específica por ID
    public Optional<Factura> revisarFacturaPorId(Long id) {
        return facturaRepository.findById(id);
    }

    // Eliminar un pedido por ID
    public boolean eliminarPedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
