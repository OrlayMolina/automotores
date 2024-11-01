package co.edu.uniquindio.automotores.domain.ports.in.cliente;

import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;

import java.util.List;
import java.util.Optional;

public interface IClienteUsesCases {

    String crearCliente(ClienteDTO clienteDTO);
    String eliminarCliente(Long id);

    void eliminarTelefonosCliente(Long nro_documento);

    String actualizarCliente(Long id, ClienteDTO clienteActualizado);
    Optional<ClienteDTO> obtenerCliente(Long id);
    List<ClienteDTO> clientes();
}
