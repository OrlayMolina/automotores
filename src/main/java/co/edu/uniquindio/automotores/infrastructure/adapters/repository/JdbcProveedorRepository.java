package co.edu.uniquindio.automotores.infrastructure.adapters.repository;

import co.edu.uniquindio.automotores.application.dto.proveedor.ProveedorDTO;
import co.edu.uniquindio.automotores.application.dto.repuesto.RepuestoDTO;
import co.edu.uniquindio.automotores.domain.exceptions.AlreadyExistsException;
import co.edu.uniquindio.automotores.domain.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.automotores.domain.model.Proveedor;
import co.edu.uniquindio.automotores.domain.ports.in.proveedor.IProveedorUsesCases;
import co.edu.uniquindio.automotores.domain.ports.in.repuesto.IRepuestoUsesCases;
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
public class JdbcProveedorRepository implements IProveedorUsesCases {

    private final DatabaseConnection databaseConnection;

    public JdbcProveedorRepository(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public String crearProveedor(ProveedorDTO proveedorDTO) {

        if(obtenerProveedor(proveedorDTO.nro_documento()).isPresent()){
            throw new AlreadyExistsException("El proveedor con numero de indectificacion: " + proveedorDTO.nro_documento() + " ya existe!");
        }

        String query = "INSERT INTO Proveedor (nro_documento, tipo_documento, telefono, correo, " +
                "nombre, razon_social)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement sentencia = connection.prepareStatement(query)) {

            atributosProveedor(proveedorDTO, sentencia);

            sentencia.executeUpdate();
            return "El proveedor fue creado correctamente.";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "El proveedor no fue registrado.";
    }
    @Override
    public String eliminarProveedor(Long nro_documento) {

        if(obtenerProveedor(nro_documento).isEmpty()){
            throw new ResourceNotFoundException("El proveedor con No. de documento: " + nro_documento + " no fue encontrado!");
        }

        String query = "DELETE FROM Proveedor WHERE nro_documento = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, nro_documento);
            int filasAfectadas = stmt.executeUpdate();
            if(filasAfectadas > 0){
                return "El proveedor fue eliminado correctamente.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "El proveedor no pudo ser eliminado.";
    }

    @Override
    public String actualizarProveedor(Long nro_documento, ProveedorDTO proveedorActualizado) {
        String query = "UPDATE Proveedor SET  = ?, nro_documento = ?, tipo_documento = ?, telefono = ?, correo = ?, " +
                "nombre = ?, razon_social = ? WHERE nro_documento = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement sentencia = connection.prepareStatement(query)) {

            atributosProveedor(proveedorActualizado, sentencia);

            sentencia.setLong(8, nro_documento);
            int filasAfectadas = sentencia.executeUpdate();
            if(filasAfectadas > 0){
                return "El Proveedor fue actualizado correctamente.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "El Proveedor no pudo ser actualizado.";
    }

    private void atributosProveedor(ProveedorDTO proveedorActualizado, PreparedStatement sentencia) throws SQLException {
        sentencia.setLong(1, proveedorActualizado.nro_documento());
        sentencia.setLong(2, proveedorActualizado.tipo_documento());
        sentencia.setString(3, proveedorActualizado.telefono());
        sentencia.setString(4, proveedorActualizado.correo());
        sentencia.setString(5, proveedorActualizado.nombre());
        sentencia.setString(6, proveedorActualizado.razon_social());
    }

    @Override
    public Optional<ProveedorDTO> obtenerProveedor(Long nro_documento) {
        String query = "SELECT * FROM Proveedor WHERE nro_documento = ?";
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, nro_documento);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                return Optional.of(mapResultSetToProveedor(result));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<ProveedorDTO> proveedor() {
        List<ProveedorDTO> repuesto = new ArrayList<>();
        String query = "SELECT * FROM Proveedor";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                repuesto.add(mapResultSetToProveedor(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedor();
    }

    private ProveedorDTO mapResultSetToProveedor(ResultSet rs) throws SQLException {

        return new ProveedorDTO(
                rs.getLong("nro_documento"),
                rs.getLong("tipo_documento"),
                rs.getString("telefono"),
                rs.getString("correo"),
                rs.getString("nombre"),
                rs.getString("razon_social"));
    }
}



