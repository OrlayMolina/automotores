package co.edu.uniquindio.automotores.application.dto.usuario;

public record MensajeDTO<T>(
        boolean error,
        T respuesta) {
}