package co.edu.uniquindio.automotores.domain.ports.in.Servicio;

import co.edu.uniquindio.automotores.application.dto.servicio.ServicioDTO;

import java.util.List;
import java.util.Optional;

public interface IServicioUsesCases {
    String crearServicio(ServicioDTO servicioDTO);
    String eliminarServicio( Long  id_servicio);

    String actualizarServicio(Long id_servicio, ServicioDTO servicioActualizado);
    Optional<ServicioDTO> obtenerServicio(Long id_service);

    List<ServicioDTO> servicios();

    ServicioDTO obtenerUnServicio(Long id_servicio);
}
