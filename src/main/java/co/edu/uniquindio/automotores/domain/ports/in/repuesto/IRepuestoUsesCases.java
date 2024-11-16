package co.edu.uniquindio.automotores.domain.ports.in.repuesto;

import co.edu.uniquindio.automotores.application.dto.repuesto.RepuestoDTO;

import java.util.List;
import java.util.Optional;

public interface IRepuestoUsesCases {
    String crearRepuesto(RepuestoDTO repuestoDTO);
    String eliminarRepuesto( String codigo_repuesto);

    String actualizarRepuesto( String codigo_repuesto, RepuestoDTO repuestoActualizado);
    Optional<RepuestoDTO> obtenerRepuesto(String codigo_repuesto);
    List<RepuestoDTO> repuesto ();

}
