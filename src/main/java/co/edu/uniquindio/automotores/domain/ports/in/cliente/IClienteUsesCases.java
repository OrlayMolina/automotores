package co.edu.uniquindio.automotores.domain.ports.in.cliente;

import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;

import java.util.List;
import java.util.Optional;

public interface IClienteUsesCases {

    String crearCliente(ClienteDTO clienteDTO);
    String eliminarCliente(Long id);
    String actualizarCliente(Long id, ClienteDTO clienteActualizado);
    Optional<ClienteDTO> obtenerCliente(Long id);

    ClienteDTO obtenerUnCliente(Long nro_documento);

    List<ClienteDTO> clientes();

    void eliminarVehiculo(Long nro_documento);
}
