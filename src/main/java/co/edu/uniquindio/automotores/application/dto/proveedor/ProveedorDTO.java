package co.edu.uniquindio.automotores.application.dto.proveedor;

public record ProveedorDTO (
        Long nro_documento,
        Long tipo_documento,
        String correo,
        String nombre,
        String razon_social)
{

}
