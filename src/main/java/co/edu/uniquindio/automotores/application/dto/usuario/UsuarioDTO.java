package co.edu.uniquindio.automotores.application.dto.usuario;

public record UsuarioDTO(
        Long id_empleado,
        String correo,
        String password,
        String estado) {
}
