package co.edu.uniquindio.automotores.application.usescases.empleado;

import co.edu.uniquindio.automotores.application.dto.empleado.EmpleadoDTO;
import co.edu.uniquindio.automotores.domain.ports.in.empleado.IEmpleadoUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcEmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements IEmpleadoUsesCases {

    private final JdbcEmpleadoRepository empleadoRepository;
    @Override
    public String crearEmpleado(EmpleadoDTO empleadoDTO) {
        return empleadoRepository.crearEmpleado(empleadoDTO);
    }

    @Override
    public String eliminarEmpleado(Long nro_documento) {
        return empleadoRepository.eliminarEmpleado(nro_documento);
    }

    @Override
    public void eliminarTelefonosEmpleado(Long nro_documento) {
        empleadoRepository.eliminarTelefonosEmpleado(nro_documento);
    }

    @Override
    public String actualizarEmpleado(Long nro_documento, EmpleadoDTO empleadoActualizado) {
        return empleadoRepository.actualizarEmpleado(nro_documento, empleadoActualizado);
    }

    @Override
    public Optional<EmpleadoDTO> obtenerEmpleado(Long nro_documento) {
        return Optional.empty();
    }

    @Override
    public List<EmpleadoDTO> empleados() {
        return empleadoRepository.empleados();
    }
}
