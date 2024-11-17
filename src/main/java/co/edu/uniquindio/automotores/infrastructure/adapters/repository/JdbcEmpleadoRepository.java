package co.edu.uniquindio.automotores.infrastructure.adapters.repository;

import co.edu.uniquindio.automotores.application.dto.empleado.EmpleadoDTO;
import co.edu.uniquindio.automotores.domain.exceptions.AlreadyExistsException;
import co.edu.uniquindio.automotores.domain.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.automotores.domain.ports.in.empleado.IEmpleadoUsesCases;
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
public class JdbcEmpleadoRepository implements IEmpleadoUsesCases {

    private final DatabaseConnection databaseConnection;
    @Override
    public String crearEmpleado(EmpleadoDTO empleadoDTO) {

        if( obtenerEmpleado( empleadoDTO.nro_documento()).isPresent()){
            throw new AlreadyExistsException("El empleado con el documento " + empleadoDTO.nro_documento() + " ya existe!");
        }
        String query = "INSERT INTO empleado (nro_documento, tipo_documento, telefono, cargo, salario, " +
                "primer_nombre, segundo_nombre, primer_apellido, segundo_apellido)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement sentencia = connection.prepareStatement(query)) {

            atributosEmpleado(empleadoDTO, sentencia);

            sentencia.executeUpdate();

            return "El Empleado fue creado correctamente.";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "El empleado no fue registrado!.";
    }

    @Override
    public String eliminarEmpleado(Long nro_documento) {

        if(obtenerEmpleado(nro_documento).isEmpty()){
            throw new ResourceNotFoundException("El empleado con numero de documento: " + nro_documento + " no fue encontrado!");
        }

        String query = "DELETE FROM Empleado WHERE nro_documento = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, nro_documento);
            int filasAfectadas = stmt.executeUpdate();
            if(filasAfectadas > 0){
                return "El Empleado fue eliminado correctamente.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "El Empleado no pudo ser eliminado.";
    }
    @Override
    public void eliminarTelefonosEmpleado(Long nro_documento) {

    }

    @Override
    public String actualizarEmpleado(Long nro_documento, EmpleadoDTO empleadoActualizado) {
        String query = "UPDATE Empleado SET  = ?, nro_documento = ?, tipo_documento = ?, telefono = ?, cargo = ?, " +
                "salario = ?, primer_nombre = ?, segundo_nombre = ? , primer_apellido = ?, segundo_apellido = ? WHERE nro_documento = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement sentencia = connection.prepareStatement(query)) {

            atributosEmpleado(empleadoActualizado, sentencia);

            sentencia.setLong(11, nro_documento);
            int filasAfectadas = sentencia.executeUpdate();
            if(filasAfectadas > 0){
                return "El empleado fue actualizado correctamente.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "El Empleado no pudo ser actualizado.";
    }
    @Override
    public Optional<EmpleadoDTO> obtenerEmpleado(Long nro_documento) {
        String query = "SELECT * FROM empleado WHERE nro_documento = ?";
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, nro_documento);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                return Optional.of(mapResultSetToEmpleado(result));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<EmpleadoDTO> empleados() {
        List<EmpleadoDTO> empleados = new ArrayList<>();
        String query = "SELECT * FROM Empleado";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                empleados.add(mapResultSetToEmpleado(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados();
    }

    @Override
    public EmpleadoDTO obtenerUnEmpleado(Long nro_documento){
        Optional<EmpleadoDTO> optEmpleado = obtenerEmpleado(nro_documento);
        return optEmpleado.orElse(null);
    }

    private void atributosEmpleado(EmpleadoDTO empleadoActualizado, PreparedStatement sentencia) throws SQLException {
        sentencia.setLong(1, empleadoActualizado.nro_documento());
        sentencia.setLong(2, empleadoActualizado.tipo_documento());
        sentencia.setString(3, empleadoActualizado.telefono());
        sentencia.setLong(4, empleadoActualizado.cargo());
        sentencia.setFloat(5, empleadoActualizado.salario());
        sentencia.setString(6, empleadoActualizado.primer_nombre());
        sentencia.setString(7, empleadoActualizado.segundo_nombre());
        sentencia.setString(8, empleadoActualizado.primer_apellido());
        sentencia.setString(9, empleadoActualizado.segundo_apellido());
    }

    private EmpleadoDTO mapResultSetToEmpleado(ResultSet rs) throws SQLException {

        return new EmpleadoDTO(
                rs.getLong("nro_documento"),
                rs.getLong("tipo_documento"),
                rs.getString("telefono"),
                rs.getLong("cargo"),
                0,
                rs.getString("primer_nombre"),
                rs.getString("segundo_nombre"),
                rs.getString("primer_apellido"),
                rs.getString("segundo_apellido"));
    }
}
