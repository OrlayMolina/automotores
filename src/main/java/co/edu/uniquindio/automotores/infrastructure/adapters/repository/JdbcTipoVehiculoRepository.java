package co.edu.uniquindio.automotores.infrastructure.adapters.repository;

import co.edu.uniquindio.automotores.application.dto.tipoVehiculo.TipoVehiculoDTO;
import co.edu.uniquindio.automotores.application.dto.tipodocumento.TipoDocumentoDTO;
import co.edu.uniquindio.automotores.domain.ports.in.tipoVehiculo.ITipoVehiculoUsesCases;
import co.edu.uniquindio.automotores.domain.ports.in.tipodocumento.ITipoDocumentoUsesCases;
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
public class JdbcTipoVehiculoRepository implements ITipoVehiculoUsesCases {

    private final DatabaseConnection databaseConnection;
    @Override
    public List<TipoVehiculoDTO> tipoVehiculoDTO() {

        List<TipoVehiculoDTO> listaTipoVehiculos = new ArrayList<>();
        String query = "SELECT * FROM tipo_Vehiculo";

        try(Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                listaTipoVehiculos.add(mapResultSetToTipoVehiculo(result));
            }

        } catch (SQLException e ){
            e.printStackTrace();
        }

        return listaTipoVehiculos;
    }

    private TipoVehiculoDTO mapResultSetToTipoVehiculo(ResultSet rs) throws SQLException {
        return new TipoVehiculoDTO(
                rs.getLong("id_tipo_vehiculo"),
                rs.getString("nombre_tipo_vehiculo")
        );
    }
}
