package com.example.proyecto.LogicaDeNegocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/Empleados")
public class EmpleadoControlador {
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
        validarDatosRequeridos(datos);
        
        // Datos comunes para todos los empleados
        String nombre = String.valueOf(datos.get("nombre"));
        int identificacion = parseInteger(datos.get("identificacion"));
        
        switch (tipo) {
            case "mesero":
                return crearMesero(nombre, identificacion, datos);
            // Aquí puedes agregar más casos para otros tipos de empleados en el futuro
            default:
                return null;
        }
    }

    private void validarDatosRequeridos(Map<String, Object> datos) {
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
        
        if (!camposFaltantes.isEmpty()) {
            throw new IllegalArgumentException("Faltan campos requeridos: " + String.join(", ", camposFaltantes));
        }
    }

    private Mesero crearMesero(String nombre, int identificacion, Map<String, Object> datos) {
        Mesero mesero = new Mesero();
        mesero.setNombre(nombre);
        mesero.setIdentificacion(identificacion);
        mesero.setSalarioBase(parseDouble(datos.get("salarioBase")));
        return mesero;
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

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEmpleadoById(@PathVariable Long id) {
        return empleadoRepository.findById(id)
                .map(empleado -> {
                    Map<String, Object> response = convertirEmpleadoAMap(empleado);
                    return ResponseEntity.ok().body(response);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private Map<String, Object> convertirEmpleadoAMap(Empleado empleado) {
        Map<String, Object> empleadoMap = new HashMap<>();
        empleadoMap.put("nombre", empleado.getNombre());
        empleadoMap.put("identificacion", empleado.getIdentificacion());
        empleadoMap.put("tipo", empleado.getClass().getSimpleName().toLowerCase());

        if (empleado instanceof Mesero) {
            empleadoMap.put("salarioBase", ((Mesero) empleado).getSalarioBase());
        }
        return empleadoMap;
    }

    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateEmpleado(
            @PathVariable Long id,
            @RequestBody Map<String, Object> empleadoData) {
        Map<String, String> response = new HashMap<>();
        
        return empleadoRepository.findById(id)
                .map(empleado -> {
                    try {
                        actualizarEmpleado(empleado, empleadoData);
                        empleadoRepository.save(empleado);
                        response.put("message", "Empleado actualizado correctamente");
                        return ResponseEntity.ok(response);
                    } catch (IllegalArgumentException e) {
                        response.put("error", "Error en los datos: " + e.getMessage());
                        return ResponseEntity.badRequest().body(response);
                    }
                })
                .orElseGet(() -> {
                    response.put("error", "Empleado no encontrado");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }

    private void actualizarEmpleado(Empleado empleado, Map<String, Object> datos) {
        if (datos.containsKey("nombre")) {
            empleado.setNombre(String.valueOf(datos.get("nombre")));
        }
        if (datos.containsKey("identificacion")) {
            empleado.setIdentificacion(parseInteger(datos.get("identificacion")));
        }
        if (empleado instanceof Mesero && datos.containsKey("salarioBase")) {
            ((Mesero) empleado).setSalarioBase(parseDouble(datos.get("salarioBase")));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmpleado(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            response.put("message", "Empleado eliminado correctamente");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Empleado no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}