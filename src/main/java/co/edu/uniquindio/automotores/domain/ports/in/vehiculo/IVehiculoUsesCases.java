package co.edu.uniquindio.automotores.domain.ports.in.vehiculo;

import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;
import co.edu.uniquindio.automotores.application.dto.vehiculo.VehiculoDTO;

import java.util.List;
import java.util.Optional;

public interface IVehiculoUsesCases {


    String crearVehiculo(VehiculoDTO vehiculoDTO);
    String eliminarVehiculo(String nro_placa);

    String actualizarVehiculo(String nro_placa, VehiculoDTO vehiculoActualizado);
    Optional<VehiculoDTO> obtenerVehiculo(String  nro_placa);
    List<VehiculoDTO> vehiculo();

    VehiculoDTO obtenerUnVehiculo(String nro_placa);
}
