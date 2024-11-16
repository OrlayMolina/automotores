package co.edu.uniquindio.automotores.application.usescases.Servicio;

import co.edu.uniquindio.automotores.application.dto.servicio.ServicioDTO;
import co.edu.uniquindio.automotores.domain.exceptions.AlreadyExistsException;
import co.edu.uniquindio.automotores.domain.ports.in.Servicio.IServicioUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements IServicioUsesCases {
    private final JdbcServicioRepository servicioRepository;

    public ServicioServiceImpl(JdbcServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public String crearServicio(ServicioDTO servicio) {

        if( obtenerServicio(servicio.id_servicio()).isPresent() ){
            throw new AlreadyExistsException( "El servicio con codigo No. " + servicio.id_servicio()+ " ya existe!");
        }

        return servicioRepository.crearServicio(servicio);
    }

    @Override
    public String eliminarServicio(Long id_servicio) {
        return servicioRepository.eliminarServicio(id_servicio);
    }

    @Override
    public String actualizarServicio(Long id_servicio, ServicioDTO servicioActualizado) {
        return servicioRepository.actualizarServicio(id_servicio, servicioActualizado);
    }

    @Override
    public Optional<ServicioDTO> obtenerServicio(Long id_servicio) {
        return servicioRepository.obtenerServicio(id_servicio);
    }

    @Override
    public List<ServicioDTO> servicio() {
        return servicioRepository.servicio();
    }
}
