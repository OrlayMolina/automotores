package co.edu.uniquindio.automotores.application.dto.repuesto;

import co.edu.uniquindio.automotores.domain.model.Proveedor;

public record RepuestoDTO(
        String codigo_repuesto,
        String nombre,
        String descripcion,
        float precio,
        int cantidad,
        Long proveedor) {


}
