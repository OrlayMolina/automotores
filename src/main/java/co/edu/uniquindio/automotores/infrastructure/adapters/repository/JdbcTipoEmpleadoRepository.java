package co.edu.uniquindio.automotores.infrastructure.adapters.repository;

import co.edu.uniquindio.automotores.application.dto.empleado.TipoEmpleadoDTO;
import co.edu.uniquindio.automotores.domain.ports.in.empleado.ITipoEmpleadoUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.database.DatabaseConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcTipoEmpleadoRepository implements ITipoEmpleadoUsesCases {

    private final DatabaseConnection databaseConnection;
    @Override
    public List<TipoEmpleadoDTO> cargos() {
        List<TipoEmpleadoDTO> cargos = new ArrayList<>();
        String query = "SELECT * FROM tipo_empleado";

        try(Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                cargos.add(mapResultSetToCargos(result));
            }

        } catch (SQLException e ){
            e.printStackTrace();
        }

        return cargos;
    }

    private TipoEmpleadoDTO mapResultSetToCargos(ResultSet rs) throws SQLException {
        return new TipoEmpleadoDTO(
                rs.getLong("id"),
                rs.getString("nombre_cargo")
        );
    }
}
