package co.edu.uniquindio.automotores.application.usescases.tipoVehiculo;

import co.edu.uniquindio.automotores.application.dto.tipoVehiculo.TipoVehiculoDTO;
import co.edu.uniquindio.automotores.application.dto.tipodocumento.TipoDocumentoDTO;
import co.edu.uniquindio.automotores.domain.ports.in.tipoVehiculo.ITipoVehiculoUsesCases;
import co.edu.uniquindio.automotores.domain.ports.in.tipodocumento.ITipoDocumentoUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcTipoDocumentoRepository;
import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcTipoVehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoVehiculoServiceImpl implements ITipoVehiculoUsesCases {

    private final JdbcTipoVehiculoRepository tipoVehiculoRepository;
    @Override
    public List<TipoVehiculoDTO> tipoVehiculos() {
        return tipoVehiculoRepository.tipoVehiculos();
    }
}
