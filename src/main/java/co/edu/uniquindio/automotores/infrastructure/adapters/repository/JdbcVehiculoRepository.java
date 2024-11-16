package co.edu.uniquindio.automotores.infrastructure.adapters.repository;


import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;
import co.edu.uniquindio.automotores.application.dto.empleado.EmpleadoDTO;
import co.edu.uniquindio.automotores.application.dto.vehiculo.VehiculoDTO;
import co.edu.uniquindio.automotores.domain.exceptions.AlreadyExistsException;
import co.edu.uniquindio.automotores.domain.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.automotores.domain.ports.in.cliente.IClienteUsesCases;
import co.edu.uniquindio.automotores.domain.ports.in.empleado.IEmpleadoUsesCases;
import co.edu.uniquindio.automotores.domain.ports.in.vehiculo.IVehiculoUsesCases;
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
public class JdbcVehiculoRepository implements IVehiculoUsesCases {

    private final DatabaseConnection databaseConnection;
    @Override
    public String crearVehiculo(VehiculoDTO vehiculoDTO) {

        if( obtenerVehiculo( vehiculoDTO.nro_placa()).isPresent()){
            throw new AlreadyExistsException("El vehiculo identificado con placa No: " + vehiculoDTO.nro_placa() + " ya existe!");
        }
        String query = "INSERT INTO vehiculo (nro_placa, tipo_vehiculo, marca, modelo, " +
                "anio_modelo, nro_motor)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement sentencia = connection.prepareStatement(query)) {

            atributosVehiculo(vehiculoDTO, sentencia);

            sentencia.executeUpdate();

            return "El vehiculo fue creado correctamente.";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "El vehiculo no fue registrado!.";
    }

    @Override
    public String eliminarVehiculo(String nro_placa) {

        if(obtenerVehiculo(nro_placa).isEmpty()){
            throw new ResourceNotFoundException("El vehiculo con placa: " + nro_placa + " no fue encontrado!");
        }

        String query = "DELETE FROM Vehiculo WHERE nro_placa = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nro_placa);
            int filasAfectadas = stmt.executeUpdate();
            if(filasAfectadas > 0){
                return "El Vehiculo fue eliminado correctamente.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "El Vehiculo no pudo ser eliminado.";
    }

    @Override
    public String actualizarVehiculo(String nro_placa, VehiculoDTO vehiculoActualizado) {
        String query = "UPDATE Vehiculo SET  = ?, nro_Placa = ?, tipo_vehiculo = ?, marca = ?, " +
                "modelo = ?, anio_modelo = ?, nro_motor = ? WHERE nro_placa = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement sentencia = connection.prepareStatement(query)) {

            atributosVehiculo(vehiculoActualizado, sentencia);

            sentencia.setString(8, nro_placa);
            int filasAfectadas = sentencia.executeUpdate();
            if(filasAfectadas > 0){
                return "El Vehiculo fue actualizado correctamente.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "El Vehiculo no pudo ser actualizado.";
    }

    private void atributosVehiculo(VehiculoDTO vehiculoActualizado, PreparedStatement sentencia) throws SQLException {
        sentencia.setString(1, vehiculoActualizado.nro_placa());
        sentencia.setLong(2, vehiculoActualizado.tipo_vehiculo());
        sentencia.setString(3,  vehiculoActualizado.marca());
        sentencia.setString(4,  vehiculoActualizado.modelo());
        sentencia.setString(5,  vehiculoActualizado.anio_modelo());
        sentencia.setString(6,  vehiculoActualizado.nro_motor());
    }

    @Override
    public Optional<VehiculoDTO> obtenerVehiculo(String nro_placa) {
        String query = "SELECT * FROM Vehiculo WHERE nro_placa = ?";
        try(Connection connection = databaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nro_placa);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                return Optional.of(mapResultSetToVehiculo(result));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<VehiculoDTO> vehiculo() {
        List<VehiculoDTO> vehiculo = new ArrayList<>();
        String query = "SELECT * FROM Vehiculo";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                vehiculo.add(mapResultSetToVehiculo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculo();
    }

    private VehiculoDTO mapResultSetToVehiculo(ResultSet rs) throws SQLException {

        return new VehiculoDTO(
                rs.getString("nro_Placa"),
                rs.getLong("tipo_vehiculo"),
                rs.getString("marca"),
                rs.getString("modelo"),
                rs.getString("anio_modelo"),
                rs.getString("nro_motor"));
    }
}


