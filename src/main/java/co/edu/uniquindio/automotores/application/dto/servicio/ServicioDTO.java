package co.edu.uniquindio.automotores.application.dto.servicio;

public record ServicioDTO(
        Long id_servicio,
        String nombre,
        String descripcion_servicio,
        float precio,
        Long servicio_asociado

){
}
