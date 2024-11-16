package co.edu.uniquindio.automotores.domain.ports.in.tipoVehiculo;

import co.edu.uniquindio.automotores.application.dto.tipoVehiculo.TipoVehiculoDTO;

import java.util.List;

public interface ITipoVehiculoUsesCases {
    List<TipoVehiculoDTO> tipoVehiculos();
}
