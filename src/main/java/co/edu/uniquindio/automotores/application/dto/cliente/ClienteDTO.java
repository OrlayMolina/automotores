package co.edu.uniquindio.automotores.application.dto.cliente;

public record ClienteDTO(
        Long nro_documento,
        Long tipo_documento,
        String correo,
        String telefono,
        String primer_nombre,
        String segundo_nombre,
        String primer_apellido,
        String segundo_apellido) {
}
