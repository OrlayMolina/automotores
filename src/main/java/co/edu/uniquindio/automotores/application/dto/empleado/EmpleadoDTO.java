package co.edu.uniquindio.automotores.application.dto.empleado;

public record EmpleadoDTO(
        Long nro_documento,
        Long tipo_documento,
        Long cargo,
        float salario,
        String primer_nombre,
        String segundo_nombre,
        String primer_apellido,
        String segundo_apellido) {
}
