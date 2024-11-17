package co.edu.uniquindio.automotores.application.usescases.proveedor;

import co.edu.uniquindio.automotores.application.dto.proveedor.ProveedorDTO;
import co.edu.uniquindio.automotores.domain.ports.in.proveedor.IProveedorUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements IProveedorUsesCases {

    private final JdbcProveedorRepository proveedorRepository;
    @Override
    public String crearProveedor(ProveedorDTO proveedorDTO) {
        return proveedorRepository.crearProveedor(proveedorDTO);
    }

    @Override
    public String eliminarProveedor(Long nro_documento) {
        return proveedorRepository.eliminarProveedor(nro_documento);
    }

    @Override
    public String actualizarProveedor(Long nro_documento, ProveedorDTO proveedorActualizado) {
        return proveedorRepository.actualizarProveedor(nro_documento, proveedorActualizado);
    }

    @Override
    public Optional<ProveedorDTO> obtenerProveedor(Long nro_documento) {
        return proveedorRepository.obtenerProveedor(nro_documento);
    }

    @Override
    public List<ProveedorDTO> proveedores() {
        return proveedorRepository.proveedores();
    }

    @Override
    public ProveedorDTO obtenerUnProveedor(Long nro_documento) {
        return proveedorRepository.obtenerUnProveedor(nro_documento);
    }

    @Override
    public void eliminarRepuestoProveeidos(Long nro_documento) {
        proveedorRepository.eliminarRepuestoProveeidos(nro_documento);
    }
}
