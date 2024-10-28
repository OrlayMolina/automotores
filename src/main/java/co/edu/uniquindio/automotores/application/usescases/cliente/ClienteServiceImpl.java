package co.edu.uniquindio.automotores.application.usescases.cliente;

import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;
import co.edu.uniquindio.automotores.domain.model.Cliente;
import co.edu.uniquindio.automotores.domain.ports.in.cliente.IClienteUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteUsesCases {

    private final JdbcClienteRepository clienteRepository;

    public ClienteServiceImpl(JdbcClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public String crearCliente(ClienteDTO cliente) {
        return clienteRepository.crearCliente(cliente);
    }

    @Override
    public String eliminarCliente(Long id) {
        return clienteRepository.eliminarCliente(id);
    }

    @Override
    public String actualizarCliente(Long id, ClienteDTO clienteActualizado) {
        return clienteRepository.actualizarCliente(id, clienteActualizado);
    }

    @Override
    public Optional<ClienteDTO> obtenerCliente(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ClienteDTO> clientes() {
        return clienteRepository.clientes();
    }
}
