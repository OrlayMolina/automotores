package co.edu.uniquindio.automotores.infrastructure.adapters.repository;

import co.edu.uniquindio.automotores.application.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.automotores.domain.ports.in.usuario.IUsuarioUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.database.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcUsuarioRepository implements IUsuarioUsesCases {

    private final DatabaseConnection databaseConnection;

    public JdbcUsuarioRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public boolean verificarPermisos() {
        return false;
    }

    public UsuarioDTO obtenerUsuarioPorCorreo(String correo){
        String query = "SELECT * FROM usuario_sistema WHERE correo = ? AND estado = 'ACTIVO'";
        try(Connection connection = databaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)){

            stmt.setString(1, correo);
            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){
                return new UsuarioDTO(
                        resultSet.getLong("id_empleado"),
                        resultSet.getString("correo"),
                        resultSet.getString("password"),
                        resultSet.getString("estado")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
