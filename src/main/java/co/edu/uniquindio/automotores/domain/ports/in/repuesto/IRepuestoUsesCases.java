package co.edu.uniquindio.automotores.domain.ports.in.repuesto;

import co.edu.uniquindio.automotores.application.dto.repuesto.RepuestoDTO;

import java.util.List;
import java.util.Optional;

public interface IRepuestoUsesCases {
    String crearRepuesto(RepuestoDTO repuestoDTO);
    String eliminarRepuesto( Long codigo_repuesto);

    String actualizarRepuesto( Long codigo_repuesto, RepuestoDTO repuestoActualizado);
    Optional<RepuestoDTO> obtenerRepuesto(Long codigo_repuesto);
    List<RepuestoDTO> repuestos();

    RepuestoDTO obtenerUnRepuesto(Long nro_documento);
}
