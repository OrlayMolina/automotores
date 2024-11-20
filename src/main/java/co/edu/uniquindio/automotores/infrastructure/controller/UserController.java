package co.edu.uniquindio.automotores.infrastructure.controller;

import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;
import co.edu.uniquindio.automotores.application.dto.empleado.EmpleadoDTO;
import co.edu.uniquindio.automotores.application.dto.proveedor.ProveedorDTO;
import co.edu.uniquindio.automotores.application.dto.repuesto.RepuestoDTO;
import co.edu.uniquindio.automotores.application.dto.servicio.ServicioDTO;
import co.edu.uniquindio.automotores.application.dto.usuario.MensajeDTO;
import co.edu.uniquindio.automotores.application.usescases.cliente.ClienteServiceImpl;
import co.edu.uniquindio.automotores.application.usescases.empleado.EmpleadoServiceImpl;
import co.edu.uniquindio.automotores.application.usescases.proveedor.ProveedorServiceImpl;
import co.edu.uniquindio.automotores.application.usescases.repuesto.RepuestoServiceImpl;
import co.edu.uniquindio.automotores.application.usescases.servicio.ServicioServiceImpl;
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
    private final ProveedorServiceImpl proveedorService;
    private final RepuestoServiceImpl repuestoService;
    private final ServicioServiceImpl servicioService;

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

    @GetMapping("/empleados/empleado/{nro_documento}")
    public ResponseEntity<MensajeDTO<EmpleadoDTO>> obtenerEmpleado(@PathVariable String nro_documento){
        EmpleadoDTO empleado = empleadoService.obtenerUnEmpleado(Long.valueOf(nro_documento));
        return ResponseEntity.ok().body(new MensajeDTO<>(false, empleado));
    }

    @PostMapping ("/empleados/actualizar-empleado/{nro_documento}")
    public ResponseEntity<MensajeDTO<String>> actualizarCliente(@PathVariable String nro_documento, @RequestBody EmpleadoDTO empleado) {
        try {
            empleadoService.actualizarEmpleado(Long.valueOf(nro_documento), empleado);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Empleado creado exitosamente"));
        } catch (AlreadyExistsException e){
            return ResponseEntity.status(CONFLICT).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @PostMapping("/proveedores/crear-proveedor")
    public ResponseEntity<MensajeDTO<String>> crearProveedor(@RequestBody ProveedorDTO proveedor) {
        try {
            proveedorService.crearProveedor(proveedor);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Proveedor creado exitosamente"));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @DeleteMapping("/proveedores/eliminar-proveedor/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarProveedor(@PathVariable String id) {
        try {
            String mensaje = proveedorService.eliminarProveedor(Long.valueOf(id));
            return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @GetMapping("/proveedores/obtener-todos")
    public ResponseEntity<MensajeDTO<List<ProveedorDTO>>> obtenerProveedores() {
        List<ProveedorDTO> proveedores = proveedorService.proveedores();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, proveedores));
    }

    @GetMapping("/proveedores/proveedor/{nro_documento}")
    public ResponseEntity<MensajeDTO<ProveedorDTO>> obtenerProveedor(@PathVariable String nro_documento) {
        ProveedorDTO proveedor = proveedorService.obtenerUnProveedor(Long.valueOf(nro_documento));
        return ResponseEntity.ok().body(new MensajeDTO<>(false, proveedor));
    }

    @PostMapping("/proveedores/actualizar-proveedor/{id}")
    public ResponseEntity<MensajeDTO<String>> actualizarProveedor(@PathVariable String id, @RequestBody ProveedorDTO proveedor) {
        try {
            proveedorService.actualizarProveedor(Long.valueOf(id), proveedor);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Proveedor actualizado exitosamente"));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @PostMapping("/repuestos/crear-repuesto")
    public ResponseEntity<MensajeDTO<String>> crearRepuesto(@RequestBody RepuestoDTO repuesto) {
        try {
            repuestoService.crearRepuesto(repuesto);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Repuesto creado exitosamente"));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @DeleteMapping("/repuestos/eliminar-repuesto/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarRepuesto(@PathVariable String id) {
        try {
            String mensaje = repuestoService.eliminarRepuesto(Long.valueOf(id));
            return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @GetMapping("/repuestos/obtener-todos")
    public ResponseEntity<MensajeDTO<List<RepuestoDTO>>> obtenerRepuestos() {
        List<RepuestoDTO> repuestos = repuestoService.repuestos();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, repuestos));
    }

    @GetMapping("/repuestos/repuesto/{id}")
    public ResponseEntity<MensajeDTO<RepuestoDTO>> obtenerRepuesto(@PathVariable String id) {
        RepuestoDTO repuesto = repuestoService.obtenerUnRepuesto(Long.valueOf(id));
        return ResponseEntity.ok().body(new MensajeDTO<>(false, repuesto));
    }

    @PostMapping("/repuestos/actualizar-repuesto/{id}")
    public ResponseEntity<MensajeDTO<String>> actualizarRepuesto(@PathVariable String id, @RequestBody RepuestoDTO repuesto) {
        try {
            repuestoService.actualizarRepuesto(Long.valueOf(id), repuesto);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Repuesto actualizado exitosamente"));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @PostMapping("/servicios/crear-servicio")
    public ResponseEntity<MensajeDTO<String>> crearServicio(@RequestBody ServicioDTO servicio) {
        try {
            servicioService.crearServicio(servicio);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Servicio creado exitosamente"));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @DeleteMapping("/servicios/eliminar-servicio/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarServicio(@PathVariable String id) {
        try {
            String mensaje = servicioService.eliminarServicio(Long.valueOf(id));
            return ResponseEntity.ok(new MensajeDTO<>(false, mensaje));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @GetMapping("/servicios/obtener-todos")
    public ResponseEntity<MensajeDTO<List<ServicioDTO>>> obtenerServicios() {
        List<ServicioDTO> servicios = servicioService.servicios();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, servicios));
    }

    @GetMapping("/servicios/servicio/{id_servicio}")
    public ResponseEntity<MensajeDTO<ServicioDTO>> obtenerServicio(@PathVariable String id_servicio) {
        ServicioDTO servicio = servicioService.obtenerUnServicio(Long.valueOf(id_servicio));
        return ResponseEntity.ok().body(new MensajeDTO<>(false, servicio));
    }

    @PostMapping("/servicios/actualizar-servicio/{id}")
    public ResponseEntity<MensajeDTO<String>> actualizarServicio(@PathVariable String id, @RequestBody ServicioDTO servicio) {
        try {
            servicioService.actualizarServicio(Long.valueOf(id), servicio);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Servicio actualizado exitosamente"));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

}
