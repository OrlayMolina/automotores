package co.edu.uniquindio.automotores.application.usescases.Vehiculo;

import co.edu.uniquindio.automotores.application.dto.repuesto.RepuestoDTO;
import co.edu.uniquindio.automotores.application.dto.vehiculo.VehiculoDTO;
import co.edu.uniquindio.automotores.domain.exceptions.AlreadyExistsException;
import co.edu.uniquindio.automotores.domain.ports.in.repuesto.IRepuestoUsesCases;
import co.edu.uniquindio.automotores.domain.ports.in.vehiculo.IVehiculoUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcRepuestoRepository;
import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcVehiculoRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements IVehiculoUsesCases {
    private final JdbcVehiculoRepository vehiculoRepository;

    public VehiculoServiceImpl(JdbcVehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public String crearVehiculo(VehiculoDTO vehiculo) {

        if( obtenerVehiculo(vehiculo.nro_placa()).isPresent() ){
            throw new AlreadyExistsException( "El vehiculo con placa Nro:" + vehiculo.nro_placa() + " ya existe!");
        }

        return vehiculoRepository.crearVehiculo(vehiculo);
    }

    @Override
    public String eliminarVehiculo(String nro_placa) {
        return vehiculoRepository.eliminarVehiculo(nro_placa);
    }

    @Override
    public String actualizarVehiculo(String nro_placa, VehiculoDTO vehiculoActualizado) {
        return vehiculoRepository.actualizarVehiculo(nro_placa, vehiculoActualizado);
    }

    @Override
    public Optional<VehiculoDTO> obtenerVehiculo(String nro_placa) {
        return vehiculoRepository.obtenerVehiculo(nro_placa);
    }

    @Override
    public List<VehiculoDTO> vehiculo() {
        return vehiculoRepository.vehiculo();
    }
}
