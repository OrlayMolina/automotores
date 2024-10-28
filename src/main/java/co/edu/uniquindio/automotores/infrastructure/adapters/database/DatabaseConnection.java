package co.edu.uniquindio.automotores.infrastructure.adapters.database;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static co.edu.uniquindio.automotores.infrastructure.adapters.config.Constants.*;

@Component
public class DatabaseConnection {

    private Connection connection = null;

    /**
     * <h1>getUserName</h1>
     * <p>Método que permite obtener del archivo properties el usuario de conexión a la base de datos
     *   MySQL utilizando las constantes en donde se define que atributo del archivo de properties
     *   consultar y su ubicación dentro del proyecto.
     *   @return Devuelve un String con el nombre de usuario de conexión de la base de datos MySQL.
     * </p>
     * <br>
     * @author Orlay Andrés Molina Gómez
     * @since 2024
     */
    private String getUsername(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RECURSO_CONNECTION);
        return resourceBundle.getString(RECURSO_DATABASE_USERNAME);
    }

    /**
     * <h1>getPassword</h1>
     * <p>Método que permite obtener del archivo properties la contraseña de conexión a la base de datos
     *   MySQL utilizando las constantes en donde se define que atributo del archivo de properties
     *   consultar y su ubicación dentro del proyecto.
     *   @return Devuelve un String con la contraseña de conexión de la base de datos MySQL.
     * </p>
     * <br>
     * @author Orlay Andrés Molina Gómez
     * @since 2024
     */
    private String getPassword(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RECURSO_CONNECTION);
        return resourceBundle.getString(RECURSO_DATABASE_PASSWORD);
    }

    /**
     * <h1>getUrl</h1>
     * <p>Método que permite obtener del archivo properties la url de conexión a la base de datos
     *   MySQL utilizando las constantes en donde se define que atributo del archivo de properties
     *   consultar y su ubicación dentro del proyecto.
     *   @return Devuelve un String con la url de conexión de la base de datos MySQL.
     * </p>
     * <br>
     * @author Orlay Andrés Molina Gómez
     * @since 2024
     */
    private String getUrl(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RECURSO_CONNECTION);
        return resourceBundle.getString(RECURSO_DATABASE_URI);
    }

    /**
     * <h1>getDriver</h1>
     * <p>
     *   Método que permite obtener del archivo properties el driver de conexión a la base de datos
     *   MySQL utilizando las constantes en donde se define que atributo del archivo de properties
     *   consultar y su ubicación dentro del proyecto.
     *   @return Devuelve un String con el driver de conexión de la base de datos MySQL.
     * </p>
     * <br>
     * @author Orlay Andrés Molina Gómez
     * @since 2024
     */
    private String getDriver(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RECURSO_CONNECTION);
        return resourceBundle.getString(RECURSO_DATABASE_DRIVER);
    }

    /**
     * <h1>getConnection</h1>
     *
     * <p>Función pública que permite establecer unaconexión a la base de datos para posteriormente poder
     *  realizar las operaciones del CRUD para los diferentes tipos de entidades de la aplicación
     *  @return un objecto de conexión de la base de datos.
     * </p>
     * <br>
     * @author Orlay Andrés Molina Gómez
     * @since 2024
     */
    public Connection getConnection(){
        try {

            Class.forName(getDriver());
            connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword());

        } catch (ClassNotFoundException e) {
            System.err.println("Ha ocurrido un ClassNotFoundException " + e.getMessage());
        } catch (SQLException e){
            System.err.println("Ha ocurrido un SQLException " + e.getMessage());
        }

        return connection;
    }
}
