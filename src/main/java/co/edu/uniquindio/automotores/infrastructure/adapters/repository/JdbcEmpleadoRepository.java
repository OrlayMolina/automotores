package co.edu.uniquindio.automotores.infrastructure.adapters.repository;

import co.edu.uniquindio.automotores.application.dto.empleado.EmpleadoDTO;
import co.edu.uniquindio.automotores.domain.ports.in.empleado.IEmpleadoUsesCases;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcEmpleadoRepository implements IEmpleadoUsesCases {
    @Override
    public String crearEmpleado(EmpleadoDTO empleadoDTO) {
        return null;
    }

    @Override
    public String eliminarEmpleado(Long nro_documento) {
        return null;
    }

    @Override
    public void eliminarTelefonosEmpleado(Long nro_documento) {

    }

    @Override
    public String actualizarEmpleado(Long nro_documento, EmpleadoDTO empleadoActualizado) {
        return null;
    }

    @Override
    public Optional<EmpleadoDTO> obtenerEmpleado(Long nro_documento) {
        return Optional.empty();
    }

    @Override
    public List<EmpleadoDTO> empleados() {
        return null;
    }
}
