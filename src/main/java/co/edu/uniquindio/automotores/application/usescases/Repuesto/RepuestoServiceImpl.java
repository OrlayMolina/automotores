package co.edu.uniquindio.automotores.application.usescases.Repuesto;

import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;
import co.edu.uniquindio.automotores.application.dto.repuesto.RepuestoDTO;
import co.edu.uniquindio.automotores.application.usescases.cliente.ClienteServiceImpl;
import co.edu.uniquindio.automotores.domain.exceptions.AlreadyExistsException;
import co.edu.uniquindio.automotores.domain.ports.in.cliente.IClienteUsesCases;
import co.edu.uniquindio.automotores.domain.ports.in.repuesto.IRepuestoUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcClienteRepository;
import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcRepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepuestoServiceImpl implements IRepuestoUsesCases {
    private final JdbcRepuestoRepository repuestoRepository;

    public RepuestoServiceImpl(JdbcRepuestoRepository repuestoeRepository) {
        this.repuestoRepository = repuestoeRepository;
    }

    @Override
    public String crearRepuesto(RepuestoDTO repuesto) {

        if( obtenerRepuesto(repuesto.codigo_repuesto()).isPresent() ){
            throw new AlreadyExistsException( "El repuesto con codigo de repuesto " + repuesto.codigo_repuesto() + " ya existe!");
        }

        return repuestoRepository.crearRepuesto(repuesto);
    }

    @Override
    public String eliminarRepuesto(String codigo_repuesto) {
        return repuestoRepository.eliminarRepuesto(codigo_repuesto);
    }

    @Override
    public String actualizarRepuesto(String codido_repuesto, RepuestoDTO repuestoActualizado) {
        return repuestoRepository.actualizarRepuesto(codido_repuesto, repuestoActualizado);
    }

    @Override
    public Optional<RepuestoDTO> obtenerRepuesto(String codigo_repuesto) {
        return repuestoRepository.obtenerRepuesto(codigo_repuesto);
    }

    @Override
    public List<RepuestoDTO> repuesto() {
        return repuestoRepository.repuesto();
    }
}
