package co.edu.uniquindio.automotores.infrastructure.adapters.repository;

import co.edu.uniquindio.automotores.application.dto.repuesto.RepuestoDTO;
import co.edu.uniquindio.automotores.domain.exceptions.AlreadyExistsException;
import co.edu.uniquindio.automotores.domain.exceptions.ResourceNotFoundException;
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
public class JdbcRepuestoRepository implements IRepuestoUsesCases {

    private final DatabaseConnection databaseConnection;

    public JdbcRepuestoRepository(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public String crearRepuesto(RepuestoDTO repuestoDTO) {

        if(obtenerRepuesto(Long.valueOf(repuestoDTO.codigo_repuesto())).isPresent()){
            throw new AlreadyExistsException("El respuesto con numero: " + repuestoDTO.codigo_repuesto() + " ya existe!");
        }

        String query = "INSERT INTO Repuesto (codigo_respuesto, nombre, descripcion, " +
                "precio, cantidad)" +
                " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement sentencia = connection.prepareStatement(query)) {

            atributosRepuesto(repuestoDTO, sentencia);

            sentencia.executeUpdate();
            return "El Repuesto fue creado correctamente.";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "El Repuesto no fue registrado.";
    }
    @Override
    public String eliminarRepuesto(Long codigo_repuesto) {

        if(obtenerRepuesto(codigo_repuesto).isEmpty()){
            throw new ResourceNotFoundException("El Repuesto con codigo: " + codigo_repuesto + " no fue encontrado!");
        }

        String query = "DELETE FROM Repuesto WHERE codigo_repuesto = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, codigo_repuesto);
            int filasAfectadas = stmt.executeUpdate();
            if(filasAfectadas > 0){
                return "El Repuesto fue eliminado correctamente.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "El Repuesto no pudo ser eliminado.";
    }

    @Override
    public String actualizarRepuesto(Long codigo_respuesto, RepuestoDTO repuestoActualizado) {
        String query = "UPDATE Repuesto SET codigo_repuesto = ?, nombre = ?, descripcion = ?, " +
                "precio = ?, cantidad = ? WHERE codigo_repuesto = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement sentencia = connection.prepareStatement(query)) {

            atributosRepuesto(repuestoActualizado, sentencia);

            sentencia.setLong(6, codigo_respuesto);
            int filasAfectadas = sentencia.executeUpdate();
            if(filasAfectadas > 0){
                return "El Repuesto fue actualizado correctamente.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "El repuesto no pudo ser actualizado.";
    }

    private void atributosRepuesto(RepuestoDTO repuestoActualizado, PreparedStatement sentencia) throws SQLException {
        sentencia.setString(1, repuestoActualizado.codigo_repuesto());
        sentencia.setString(2, repuestoActualizado.nombre());
        sentencia.setString(3, repuestoActualizado.descripcion());
        sentencia.setFloat(4, repuestoActualizado.precio());
        sentencia.setInt(5, repuestoActualizado.cantidad());
    }

    @Override
    public Optional<RepuestoDTO> obtenerRepuesto(Long codigo_repuesto) {
        String query = "SELECT * FROM Repuesto WHERE codigo_repuesto = ?";
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, codigo_repuesto);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                return Optional.of(mapResultSetToRepuesto(result));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<RepuestoDTO> repuestos() {
        List<RepuestoDTO> repuestos = new ArrayList<>();
        String query = "SELECT * FROM Repuesto";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                repuestos.add(mapResultSetToRepuesto(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repuestos;
    }

    @Override
    public RepuestoDTO obtenerUnRepuesto(Long nro_documento){
        Optional<RepuestoDTO> optRepuesto = obtenerRepuesto(nro_documento);
        return optRepuesto.orElse(null);
    }

    private RepuestoDTO mapResultSetToRepuesto(ResultSet rs) throws SQLException {

        return new RepuestoDTO(
                rs.getString("codigo_repuesto"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getFloat("precio"),
                rs.getInt("cantidad"));
            }
}


