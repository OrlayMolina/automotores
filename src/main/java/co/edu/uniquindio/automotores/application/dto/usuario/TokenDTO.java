package co.edu.uniquindio.automotores.application.dto.usuario;

import jakarta.validation.constraints.NotNull;

public record TokenDTO(
        @NotNull String token) {
}
