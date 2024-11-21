package co.edu.uniquindio.automotores.application.usescases.reporte;

import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcReportesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteServiceImpl {

    private final JdbcReportesRepository reportesRepository;

    public ReporteServiceImpl(JdbcReportesRepository reportesRepository) {
        this.reportesRepository = reportesRepository;
    }

    public List<String> listarClientesConCorreoYTelefono() {
        return reportesRepository.listarClientesConCorreoYTelefono();
    }

    public List<String> listarEmpleadosConCargo() {
        return reportesRepository.listarEmpleadosConCargo();
    }

    public List<String> listarServiciosConPrecios() {
        return reportesRepository.listarServiciosConPrecios();
    }

    public List<String> listarOrdenesPorCliente() {
        return reportesRepository.listarOrdenesPorCliente();
    }

    public List<String> totalServiciosPorEmpleado() {
        return reportesRepository.totalServiciosPorEmpleado();
    }

    public List<String> totalFacturasPorMes() {
        return reportesRepository.totalFacturasPorMes();
    }

    public List<String> repuestosMasVendidos() {
        return reportesRepository.repuestosMasVendidos();
    }

    public List<String> ingresosPorCliente() {
        return reportesRepository.ingresosPorCliente();
    }

    public List<String> detalleOrdenesConServiciosYRepuestos() {
        return reportesRepository.detalleOrdenesConServiciosYRepuestos();
    }

    public List<String> ingresosMensualesDesglosados() {
        return reportesRepository.ingresosMensualesDesglosados();
    }
}
