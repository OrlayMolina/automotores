package co.edu.uniquindio.automotores.application.usescases.cliente;

import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;
import co.edu.uniquindio.automotores.domain.exceptions.AlreadyExistsException;
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

        if( obtenerCliente(cliente.nro_documento()).isPresent() ){
            throw new AlreadyExistsException( "El Cliente con el nro de documento " + cliente.nro_documento() + " ya existe!");
        }

        return clienteRepository.crearCliente(cliente);
    }

    @Override
    public String eliminarCliente(Long nro_documento) {
        return clienteRepository.eliminarCliente(nro_documento);
    }

    @Override
    public void eliminarTelefonosCliente(Long nro_documento) {
        clienteRepository.eliminarTelefonosCliente(nro_documento);
    }

    @Override
    public String actualizarCliente(Long id, ClienteDTO clienteActualizado) {
        return clienteRepository.actualizarCliente(id, clienteActualizado);
    }

    @Override
    public Optional<ClienteDTO> obtenerCliente(Long nro_documento) {
        return clienteRepository.obtenerCliente(nro_documento);
    }

    @Override
    public ClienteDTO obtenerUnCliente(Long nro_documento) {
        return clienteRepository.obtenerUnCliente(nro_documento);
    }

    @Override
    public List<ClienteDTO> clientes() {
        return clienteRepository.clientes();
    }
}
