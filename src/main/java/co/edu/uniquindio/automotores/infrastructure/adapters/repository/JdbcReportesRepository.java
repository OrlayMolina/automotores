package co.edu.uniquindio.automotores.infrastructure.adapters.repository;

import co.edu.uniquindio.automotores.infrastructure.adapters.database.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcReportesRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcReportesRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * Reporte 1: Listar todos los clientes con su nombre completo y correo.
     */
    public List<String> listarClientesConCorreoYTelefono() {
        String query = "SELECT CONCAT(primer_nombre, ' ', segundo_apellido, ' ', primer_apellido, ' ', segundo_apellido) AS nombre_completo, correo, telefono FROM cliente";
        return ejecutarConsultaSimple(query);
    }

    /**
     * Reporte 2: Listar todos los empleados con su cargo.
     */
    public List<String> listarEmpleadosConCargo() {
        String query = """
            SELECT CONCAT(e.primer_nombre, ' ', e.primer_apellido) AS nombre_completo, t.nombre_cargo, salario 
            FROM empleado e
            JOIN tipo_empleado t ON e.cargo = t.id
        """;
        return ejecutarConsultaSimple(query);
    }

    /**
     * Reporte 3: Listar todos los servicios con su precio.
     */
    public List<String> listarServiciosConPrecios() {
        String query = "SELECT nombre, precio FROM servicio";
        return ejecutarConsultaSimple(query);
    }

    // Reportes Intermedios

    /**
     * Reporte 4: Obtener las órdenes de servicio realizadas por cada cliente.
     */
    public List<String> listarOrdenesPorCliente() {
        String query = """
            SELECT c.nro_documento, CONCAT(c.primer_nombre, ' ', c.primer_apellido) AS nombre_cliente, COUNT(o.codigo_orden) AS total_ordenes
            FROM cliente c
            LEFT JOIN orden o ON c.nro_documento = o.cliente
            GROUP BY c.nro_documento
        """;
        return ejecutarConsultaSimple(query);
    }

    /**
     * Reporte 5: Total de servicios realizados por cada empleado.
     */
    public List<String> totalServiciosPorEmpleado() {
        String query = """
            SELECT e.nro_documento, CONCAT(e.primer_nombre, ' ', e.primer_apellido) AS nombre_empleado, COUNT(eo.id) AS total_servicios
            FROM empleado e
            LEFT JOIN empleado_orden eo ON e.nro_documento = eo.empleado
            GROUP BY e.nro_documento
        """;
        return ejecutarConsultaSimple(query);
    }

    /**
     * Reporte 6: Total de facturas generadas y su valor total por mes.
     */
    public List<String> totalFacturasPorMes() {
        String query = """
            SELECT DATE_FORMAT(f.fecha, '%Y-%m') AS mes, COUNT(f.codigo_factura) AS total_facturas, SUM(f.total) AS total_recaudado
            FROM factura f
            GROUP BY DATE_FORMAT(f.fecha, '%Y-%m')
        """;
        return ejecutarConsultaSimple(query);
    }

    /**
     * Reporte 7: Listar los repuestos más vendidos con su cantidad total.
     */
    public List<String> repuestosMasVendidos() {
        String query = """
            SELECT r.nombre, SUM(fd.cantidad) AS total_vendido
            FROM factura_detalle fd
            JOIN repuesto r ON fd.repuesto = r.codigo_repuesto
            GROUP BY r.nombre
            ORDER BY total_vendido DESC
        """;
        return ejecutarConsultaSimple(query);
    }

    // Reportes Complejos

    /**
     * Reporte 8: Obtener el ingreso total por cliente, considerando facturas y servicios asociados.
     */
    public List<String> ingresosPorCliente() {
        String query = """
            SELECT c.nro_documento, CONCAT(c.primer_nombre, ' ', c.primer_apellido) AS nombre_cliente, SUM(f.total) AS ingreso_total
            FROM cliente c
            LEFT JOIN factura f ON c.nro_documento = f.cliente
            GROUP BY c.nro_documento
        """;
        return ejecutarConsultaSimple(query);
    }

    /**
     * Reporte 9: Listar las órdenes con su detalle de servicios y repuestos utilizados.
     */
    public List<String> detalleOrdenesConServiciosYRepuestos() {
        String query = """
            SELECT o.codigo_orden, s.nombre AS servicio, r.nombre AS repuesto, od.cantidad, od.subtotal
            FROM orden o
            LEFT JOIN orden_detalle od ON o.codigo_orden = od.orden_servicio
            LEFT JOIN servicio s ON od.servicio = s.id_servicio
            LEFT JOIN repuesto r ON od.repuesto = r.codigo_repuesto
        """;
        return ejecutarConsultaSimple(query);
    }

    /**
     * Reporte 10: Generar un listado de ingresos mensuales desglosados por cliente, servicio y repuestos.
     */
    public List<String> ingresosMensualesDesglosados() {
        String query = """
            SELECT DATE_FORMAT(f.fecha, '%Y-%m') AS mes, c.nro_documento, CONCAT(c.primer_nombre, ' ', c.primer_apellido) AS nombre_cliente, 
                   s.nombre AS servicio, r.nombre AS repuesto, SUM(fd.precio * fd.cantidad) AS total
            FROM factura f
            LEFT JOIN cliente c ON f.cliente = c.nro_documento
            LEFT JOIN factura_detalle fd ON f.codigo_factura = fd.codigo_factura
            LEFT JOIN servicio s ON fd.servicio = s.id_servicio
            LEFT JOIN repuesto r ON fd.repuesto = r.codigo_repuesto
            GROUP BY mes, c.nro_documento, servicio, repuesto
            ORDER BY mes, total DESC
        """;
        return ejecutarConsultaSimple(query);
    }

    private List<String> ejecutarConsultaSimple(String query) {
        List<String> resultados = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            int columnas = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                StringBuilder fila = new StringBuilder();
                for (int i = 1; i <= columnas; i++) {
                    fila.append(rs.getString(i)).append(i < columnas ? " | " : "");
                }
                resultados.add(fila.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
    }
}
