package co.edu.uniquindio.automotores.domain.ports.in.proveedor;

import co.edu.uniquindio.automotores.application.dto.proveedor.ProveedorDTO;
import co.edu.uniquindio.automotores.application.dto.repuesto.RepuestoDTO;

import java.util.List;
import java.util.Optional;

public interface IProveedorUsesCases {
    String crearProveedor(ProveedorDTO proveedorDTO);
    String eliminarProveedor(Long nro_documento);

    String actualizarProveedor(Long nro_documento, ProveedorDTO proveedorActualizado);
    Optional<ProveedorDTO> obtenerProveedor(Long nro_documento);
    List<ProveedorDTO> proveedores();

    ProveedorDTO obtenerUnProveedor(Long nro_documento);

    void eliminarRepuestoProveeidos(Long nro_documento);
}

