package co.edu.uniquindio.automotores.infrastructure.adapters.report;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.List;
/*import java.util.Map;

@Component("/ruta/?format=pdf")
public class ListarClientesPDF extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Cliente> listadoClientes = (List<Cliente>) model.get("clientes");

        PdfPTable tablaClientes = new PdfPTable(6);

        listadoClientes.forEach(cliente -> {
            tablaClientes.addCell(cliente.getId().toString());
            tablaClientes.addCell(cliente.getNombres());
            tablaClientes.addCell(cliente.getApellidos());
            tablaClientes.addCell(cliente.getTelefono());
            tablaClientes.addCell(cliente.getEmail());
            tablaClientes.addCell(cliente.getCiudad().getCiudad());
        });

        document.add(tablaClientes);
    }
}
*/