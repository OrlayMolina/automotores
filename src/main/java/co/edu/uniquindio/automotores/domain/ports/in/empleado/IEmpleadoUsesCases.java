package co.edu.uniquindio.automotores.domain.ports.in.empleado;

import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;
import co.edu.uniquindio.automotores.application.dto.empleado.EmpleadoDTO;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoUsesCases {

    String crearEmpleado(EmpleadoDTO empleadoDTO);
    String eliminarEmpleado(Long nro_documento);

    void eliminarTelefonosEmpleado(Long nro_documento);

    String actualizarEmpleado(Long nro_documento, EmpleadoDTO empleadoActualizado);
    Optional<EmpleadoDTO> obtenerEmpleado(Long nro_documento);
    List<EmpleadoDTO> empleados();

    EmpleadoDTO obtenerUnEmpleado(Long nro_documento);
}
