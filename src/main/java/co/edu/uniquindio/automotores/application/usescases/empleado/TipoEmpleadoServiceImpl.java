package co.edu.uniquindio.automotores.application.usescases.empleado;

import co.edu.uniquindio.automotores.application.dto.empleado.TipoEmpleadoDTO;
import co.edu.uniquindio.automotores.domain.ports.in.empleado.ITipoEmpleadoUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcTipoEmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoEmpleadoServiceImpl implements ITipoEmpleadoUsesCases {

    private final JdbcTipoEmpleadoRepository empleadoRepository;
    @Override
    public List<TipoEmpleadoDTO> cargos() {
        return empleadoRepository.cargos();
    }
}
