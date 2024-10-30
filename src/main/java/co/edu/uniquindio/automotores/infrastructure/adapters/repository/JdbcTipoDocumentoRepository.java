package co.edu.uniquindio.automotores.infrastructure.adapters.repository;

import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;
import co.edu.uniquindio.automotores.application.dto.tipodocumento.TipoDocumentoDTO;
import co.edu.uniquindio.automotores.domain.exceptions.ResourceNotFoundException;
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
public class JdbcTipoDocumentoRepository implements ITipoDocumentoUsesCases {

    private final DatabaseConnection databaseConnection;
    @Override
    public List<TipoDocumentoDTO> tipoDocumentos() {

        List<TipoDocumentoDTO> listaTiposDocumnetos = new ArrayList<>();
        String query = "SELECT * FROM tipo_documento";

        try(Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                listaTiposDocumnetos.add(mapResultSetToTipoDocumento(result));
            }

        } catch (SQLException e ){
            e.printStackTrace();
        }

        return listaTiposDocumnetos;
    }

    private TipoDocumentoDTO mapResultSetToTipoDocumento(ResultSet rs) throws SQLException {
        return new TipoDocumentoDTO(
                rs.getLong("id"),
                rs.getString("abreviacion"),
                rs.getString("descripcion")
        );
    }
}
