package co.edu.uniquindio.automotores.domain.model;

public class Servicio {
    private Long id_servicio;
    private String nombre;
    private String descripcion_servicio;
    private float precio;
    private Long servicio_asociado;

    public Servicio() {
    }

    public Servicio(Long id_servicio, String nombre, String descripcion_servicio, float precio, Long servicio_asociado) {
        this.id_servicio = id_servicio;
        this.nombre = nombre;
        this.descripcion_servicio = descripcion_servicio;
        this.precio = precio;
        this.servicio_asociado = servicio_asociado;
    }

    public Long getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Long id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion_servicio() {
        return descripcion_servicio;
    }

    public void setDescripcion_servicio(String descripcion_servicio) {
        this.descripcion_servicio = descripcion_servicio;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Long getServicio_asociado() {
        return servicio_asociado;
    }

    public void setServicio_asociado(Long servicio_asociado) {
        this.servicio_asociado = servicio_asociado;
    }
}
