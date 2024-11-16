package co.edu.uniquindio.automotores.domain.model;

public class Repuesto {
    private String codigo_repuesto;
    private String nombre;
    private String descripcion;
    private Float precio;
    private int cantidad;
    private Proveedor proveedor;

    public Repuesto(){}

    public String getCodigo_repuesto() {
        return codigo_repuesto;
    }

    public void setCodigo_repuesto(String codigo_repuesto) {
        this.codigo_repuesto = codigo_repuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
