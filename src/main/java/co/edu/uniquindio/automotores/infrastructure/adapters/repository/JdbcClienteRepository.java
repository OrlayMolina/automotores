package co.edu.uniquindio.automotores.infrastructure.adapters.repository;

import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;
import co.edu.uniquindio.automotores.domain.ports.in.cliente.IClienteUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.database.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClienteRepository implements IClienteUsesCases {

    private final DatabaseConnection databaseConnection;

    public JdbcClienteRepository(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public String crearCliente(ClienteDTO clienteDTO) {
        String query = "INSERT INTO Cliente (nro_documento, tipo_documento, correo, " +
                "primer_nombre, segundo_nombre, primer_apellido, segundo_apellido)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement sentencia = connection.prepareStatement(query)) {

            atributosCliente(clienteDTO, sentencia);

            sentencia.executeUpdate();
            return "El Cliente fue creado correctamente.";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "El cliente no fue registrado.";
    }

    @Override
    public String eliminarCliente(Long id) {
        String query = "DELETE FROM Cliente WHERE nro_documento = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if(filasAfectadas > 0){
                return "El cliente fue eliminado correctamente.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "El cliente no pudo ser eliminado.";
    }

    @Override
    public String actualizarCliente(Long id, ClienteDTO clienteActualizado) {
        String query = "UPDATE Cliente SET nro_documento = ?, tipo_documento = ?, correo = ?, primer_nombre = ?, " +
                "segundo_nombre = ?, primer_apellido = ?, segundo_apellido = ? WHERE nro_documento = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement sentencia = connection.prepareStatement(query)) {

            atributosCliente(clienteActualizado, sentencia);

            sentencia.setLong(8, id);
            int filasAfectadas = sentencia.executeUpdate();
            if(filasAfectadas > 0){
                return "El cliente fue actualizado correctamente.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "El cliente no pudo ser actualizado.";
    }

    private void atributosCliente(ClienteDTO clienteActualizado, PreparedStatement sentencia) throws SQLException {
        sentencia.setLong(1, clienteActualizado.nro_documento());
        sentencia.setLong(2, clienteActualizado.tipo_documento());
        sentencia.setString(3, clienteActualizado.correo());
        sentencia.setString(4, clienteActualizado.primer_nombre());
        sentencia.setString(5, clienteActualizado.segundo_nombre());
        sentencia.setString(6, clienteActualizado.primer_apellido());
        sentencia.setString(7, clienteActualizado.segundo_apellido());
    }

    @Override
    public Optional<ClienteDTO> obtenerCliente(Long id) {
        String query = "SELECT * FROM Cliente WHERE nro_documento = ?";
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                return Optional.of(mapResultSetToCliente(result));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ClienteDTO> clientes() {
        List<ClienteDTO> clientes = new ArrayList<>();
        String query = "SELECT * FROM Cliente";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                clientes.add(mapResultSetToCliente(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    private ClienteDTO mapResultSetToCliente(ResultSet rs) throws SQLException {

        return new ClienteDTO(
                rs.getLong("nro_documento"),
                rs.getLong("tipo_documento"),
                rs.getString("correo_electronico"),
                rs.getString("primer_nombre"),
                rs.getString("segundo_nombre"),
                rs.getString("primer_apellido"),
                rs.getString("segundo_apellido"));
    }
}
