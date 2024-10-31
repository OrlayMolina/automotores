package co.edu.uniquindio.automotores.domain.ports.in.empleado;

import co.edu.uniquindio.automotores.application.dto.empleado.TipoEmpleadoDTO;

import java.util.List;

public interface ITipoEmpleadoUsesCases {

    List<TipoEmpleadoDTO> cargos();
}
