package co.edu.uniquindio.automotores.infrastructure.adapters.repository;

import co.edu.uniquindio.automotores.application.dto.servicio.ServicioDTO;
import co.edu.uniquindio.automotores.domain.exceptions.AlreadyExistsException;
import co.edu.uniquindio.automotores.domain.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.automotores.domain.ports.in.Servicio.IServicioUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.database.DatabaseConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcServicioRepository implements IServicioUsesCases {

    private final DatabaseConnection databaseConnection;

    @Override
    public String crearServicio(ServicioDTO servicioDTO) {

        if(obtenerServicio(servicioDTO.id_servicio()).isPresent()){
            throw new AlreadyExistsException("El servicio con numero: " + servicioDTO.id_servicio() + " ya existe!");
        }

        String query = "INSERT INTO Servicio (id_Servicio, nombre, descripcion_servicio, " +
                "precio, servicio_asociado)" +
                " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement sentencia = connection.prepareStatement(query)) {

            atributosServicio(servicioDTO, sentencia);

            sentencia.executeUpdate();
            return "El Servicio fue creado correctamente.";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "El Servicio no fue registrado.";
    }
    @Override
    public String eliminarServicio(Long id_servicio) {

        if(obtenerServicio(id_servicio).isEmpty()){
            throw new ResourceNotFoundException("El Servicio con codigo: " + id_servicio + " no fue encontrado!");
        }

        String query = "DELETE FROM Servicio WHERE id_servicio = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id_servicio);
            int filasAfectadas = stmt.executeUpdate();
            if(filasAfectadas > 0){
                return "El servicio fue eliminado correctamente.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "El servicio no pudo ser eliminado.";
    }

    @Override
    public String actualizarServicio(Long id_servicio, ServicioDTO servicioActualizado) {
        String query = "UPDATE Servicio SET  = ?, id_servicio = ?, nombre = ?, descripcion_servicio = ?, " +
                "precio = ?, servicio_asociado = ? WHERE id_servicio = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement sentencia = connection.prepareStatement(query)) {

            atributosServicio(servicioActualizado, sentencia);

            sentencia.setLong(8,id_servicio);
            int filasAfectadas = sentencia.executeUpdate();
            if(filasAfectadas > 0){
                return "El Servicio fue actualizado correctamente.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "El Servicio no pudo ser actualizado.";
    }

    @Override
    public Optional<ServicioDTO> obtenerServicio(Long id_servicio) {
        String query = "SELECT * FROM Servicio WHERE id_servicio = ?";
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id_servicio);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                return Optional.of(mapResultSetToServicio(result));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private void atributosServicio(ServicioDTO servicioActualizado, PreparedStatement sentencia) throws SQLException {
        sentencia.setLong(1, servicioActualizado.id_servicio());
        sentencia.setString(2, servicioActualizado.nombre());
        sentencia.setString(3, servicioActualizado.descripcion_servicio());
        sentencia.setFloat(4,servicioActualizado.precio());
        sentencia.setLong(5, servicioActualizado.servicio_asociado());
    }

    @Override
    public List<ServicioDTO> servicio() {
        List<ServicioDTO> servicio = new ArrayList<>();
        String query = "SELECT * FROM Servicio";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                servicio.add(mapResultSetToServicio(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicio();
    }

    private ServicioDTO mapResultSetToServicio(ResultSet rs) throws SQLException {

        return new ServicioDTO(
                rs.getLong("id_servicio"),
                rs.getString("nombre"),
                rs.getString("descripcion_servicio"),
                rs.getFloat("precio"),
                rs.getLong("servicio_asociado"));
    }
}


