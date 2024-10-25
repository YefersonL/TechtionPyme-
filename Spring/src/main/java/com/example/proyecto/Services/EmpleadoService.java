package com.example.proyecto.Services;

import com.example.proyecto.LogicaDeNegocio.Empleado;
import com.example.proyecto.LogicaDeNegocio.Mesero;
import com.example.proyecto.LogicaDeNegocio.Cocinero;
import com.example.proyecto.Persistencia.EmpleadoRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmpleadoService {
   @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @PostMapping
    public ResponseEntity<Map<String, String>> createEmpleado(@RequestBody Map<String, Object> empleadoData) {
        Map<String, String> response = new HashMap<>();
        try {
            String tipo = String.valueOf(empleadoData.get("tipo")).toLowerCase();
            Empleado empleado = crearEmpleadoPorTipo(tipo, empleadoData);
            
            if (empleado == null) {
                response.put("error", "Tipo de empleado no válido. Tipos válidos: mesero");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            empleadoRepository.save(empleado);
            response.put("message", "Empleado agregado correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("error", "Error en los datos: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "Error al agregar empleado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private Empleado crearEmpleadoPorTipo(String tipo, Map<String, Object> datos) {
        // Validar que los datos requeridos estén presentes
        validarDatosRequeridos(datos, tipo);
        
        // Datos comunes para todos los empleados
        String nombre = String.valueOf(datos.get("nombre"));
        int identificacion = parseInteger(datos.get("identificacion"));
        double salarioBase = parseDouble(datos.get("salarioBase"));
        
        switch (tipo) {
            case "mesero":
                return crearMesero(nombre, identificacion, salarioBase, datos);
            
            case "cocinero":
                return crearCocinero(nombre, identificacion,salarioBase, datos);
              
            default:
                return null;
                
        }
    }

   private void validarDatosRequeridos(Map<String, Object> datos, String tipo) {
        List<String> camposFaltantes = new ArrayList<>();
        
        if (!datos.containsKey("nombre") || datos.get("nombre") == null) {
            camposFaltantes.add("nombre");
        }
        if (!datos.containsKey("identificacion") || datos.get("identificacion") == null) {
            camposFaltantes.add("identificacion");
        }
        if (!datos.containsKey("salarioBase") || datos.get("salarioBase") == null) {
            camposFaltantes.add("salarioBase");
        }
        
        if ("mesero".equals(tipo) && (!datos.containsKey("turno") || datos.get("turno") == null)) {
            camposFaltantes.add("turno");
        }
        
        if ("cocinero".equals(tipo) && (!datos.containsKey("especialidad") || datos.get("especialidad") == null)) {
            camposFaltantes.add("especialidad");
        }
        if (!camposFaltantes.isEmpty()) {
            throw new IllegalArgumentException("Faltan campos requeridos: " + String.join(", ", camposFaltantes));
        }
    }

    private Mesero crearMesero(String nombre, int identificacion, double salarioBase, Map<String, Object> datos) {
        Mesero mesero = new Mesero();
        mesero.setNombre(nombre);
        mesero.setIdentificacion(identificacion);
        mesero.setSalarioBase(salarioBase);
        mesero.setTurno(String.valueOf(datos.get("turno")));
        return mesero;
    }
    
    private Cocinero crearCocinero(String nombre, int identificacion,double salarioBase, Map<String, Object> datos) {
        Cocinero cocinero = new Cocinero();
        cocinero.setNombre(nombre);
        cocinero.setIdentificacion(identificacion);
        cocinero.setSalarioBase(salarioBase);
        cocinero.setEspecialidad(String.valueOf(datos.get("especialidad")));
        return cocinero;
    }

    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public Map<String, String> updateEmpleado(Long id, Map<String, Object> empleadoData) {
        Map<String, String> response = new HashMap<>();
        return empleadoRepository.findById(id)
                .map(empleado -> {
                    actualizarEmpleado(empleado, empleadoData);
                    empleadoRepository.save(empleado);
                    response.put("message", "Empleado actualizado correctamente");
                    return response;
                })
                .orElseGet(() -> {
                    response.put("error", "Empleado no encontrado");
                    return response;
                });
    }

    private void actualizarEmpleado(Empleado empleado, Map<String, Object> datos) {
        if (datos.containsKey("nombre")) {
            empleado.setNombre(String.valueOf(datos.get("nombre")));
        }
        if (datos.containsKey("identificacion")) {
            empleado.setIdentificacion(parseInteger(datos.get("identificacion")));
        }
        
        if (datos.containsKey("salarioBase")) {
            empleado.setSalarioBase(parseDouble(datos.get("salarioBase")));
        }
        
        
        if (empleado instanceof Cocinero && datos.containsKey("especialidad")) {
            ((Cocinero) empleado).setEspecialidad("especialidad");
        }
        
    }

    public Map<String, String> deleteEmpleado(Long id) {
        Map<String, String> response = new HashMap<>();
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            response.put("message", "Empleado eliminado correctamente");
        } else {
            response.put("error", "Empleado no encontrado");
        }
        return response;
    }
    
    private int parseInteger(Object value) {
        try {
            if (value instanceof Integer) {
                return (Integer) value;
            }
            if (value instanceof String) {
                return Integer.parseInt((String) value);
            }
            if (value instanceof Number) {
                return ((Number) value).intValue();
            }
            
            throw new IllegalArgumentException("Formato inválido para número entero: " + value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El valor debe ser un número entero válido");
        }
    }

    private double parseDouble(Object value) {
        try {
            if (value instanceof Double) {
                return (Double) value;
            }
            if (value instanceof String) {
                return Double.parseDouble((String) value);
            }
            if (value instanceof Number) {
                return ((Number) value).doubleValue();
            }
            throw new IllegalArgumentException("Formato inválido para número decimal: " + value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El valor debe ser un número decimal válido");
        }
    }
}
