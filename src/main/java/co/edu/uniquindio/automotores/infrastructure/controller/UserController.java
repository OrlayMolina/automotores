package co.edu.uniquindio.automotores.infrastructure.controller;

import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;
import co.edu.uniquindio.automotores.application.dto.empleado.EmpleadoDTO;
import co.edu.uniquindio.automotores.application.dto.usuario.MensajeDTO;
import co.edu.uniquindio.automotores.application.usescases.cliente.ClienteServiceImpl;
import co.edu.uniquindio.automotores.application.usescases.empleado.EmpleadoServiceImpl;
import co.edu.uniquindio.automotores.domain.exceptions.AlreadyExistsException;
import co.edu.uniquindio.automotores.domain.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final ClienteServiceImpl clienteService;
    private final EmpleadoServiceImpl empleadoService;

    @PostMapping ("/clientes/crear-cliente")
    public ResponseEntity<MensajeDTO<String>> crearCliente(@RequestBody ClienteDTO cliente) {
        try {
            clienteService.crearCliente(cliente);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cliente creado exitosamente"));
        } catch (AlreadyExistsException e){
            return ResponseEntity.status(CONFLICT).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @DeleteMapping("/clientes/eliminar-cliente/{nro_documento}")
    public ResponseEntity<MensajeDTO<String>> eliminarCliente(@PathVariable String nro_documento){
        try {
            String mensaje = clienteService.eliminarCliente(Long.valueOf(nro_documento));
            return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
        } catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @GetMapping("/clientes/obtener-todos")
    public ResponseEntity<MensajeDTO<List<ClienteDTO>>> obtenerClientes(){
        List<ClienteDTO> clientes = clienteService.clientes();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clientes));
    }

    @GetMapping("/clientes/cliente/{nro_documento}")
    public ResponseEntity<MensajeDTO<ClienteDTO>> obtenerCliente(@PathVariable String nro_documento){
        ClienteDTO cliente = clienteService.obtenerUnCliente(Long.valueOf(nro_documento));
        return ResponseEntity.ok().body(new MensajeDTO<>(false, cliente));
    }

    @PostMapping ("/clientes/actualizar-cliente/{nro_documento}")
    public ResponseEntity<MensajeDTO<String>> actualizarCliente(@PathVariable String nro_documento, @RequestBody ClienteDTO cliente) {
        try {
            clienteService.actualizarCliente(Long.valueOf(nro_documento), cliente);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cliente creado exitosamente"));
        } catch (AlreadyExistsException e){
            return ResponseEntity.status(CONFLICT).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @PostMapping ("/empleados/crear-empleado")
    public ResponseEntity<MensajeDTO<String>> crearEmpleado(@RequestBody EmpleadoDTO empleado) {
        try {
            empleadoService.crearEmpleado(empleado);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Empleado creado exitosamente"));
        } catch (AlreadyExistsException e){
            return ResponseEntity.status(CONFLICT).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @DeleteMapping("/empleados/eliminar-empleado/{nro_documento}")
    public ResponseEntity<MensajeDTO<String>> eliminarEmpleado(@PathVariable String nro_documento){
        try {
            String mensaje = empleadoService.eliminarEmpleado(Long.valueOf(nro_documento));
            return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
        } catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @GetMapping("/empleados/obtener-todos")
    public ResponseEntity<MensajeDTO<List<EmpleadoDTO>>> obtenerEmpleados(){
        List<EmpleadoDTO> empleados = empleadoService.empleados();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, empleados));
    }

    @GetMapping("/empleados/cliente/{nro_documento}")
    public ResponseEntity<MensajeDTO<EmpleadoDTO>> obtenerEmpleado(@PathVariable String nro_documento){
        EmpleadoDTO empleado = empleadoService.obtenerUnEmpleado(Long.valueOf(nro_documento));
        return ResponseEntity.ok().body(new MensajeDTO<>(false, empleado));
    }

    @PostMapping ("/empleados/actualizar-cliente/{nro_documento}")
    public ResponseEntity<MensajeDTO<String>> actualizarCliente(@PathVariable String nro_documento, @RequestBody EmpleadoDTO empleado) {
        try {
            empleadoService.actualizarEmpleado(Long.valueOf(nro_documento), empleado);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Empleado creado exitosamente"));
        } catch (AlreadyExistsException e){
            return ResponseEntity.status(CONFLICT).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }
}
