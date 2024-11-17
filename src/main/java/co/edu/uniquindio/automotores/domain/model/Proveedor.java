package co.edu.uniquindio.automotores.domain.model;

public class Proveedor {
    private Long nro_documento;
    private Long tipo_documento;
    private String telefono;
    private String correo;
    private String nombre;
    private String razon_social;

    public Proveedor(){}

    public Proveedor(Long nro_documento, Long tipo_documento, String correo, String nombre, String razon_social) {
        this.nro_documento = nro_documento;
        this.tipo_documento = tipo_documento;
        this.correo = correo;
        this.nombre = nombre;
        this.razon_social = razon_social;
    }

    public Long getNro_documento() {
        return nro_documento;
    }

    public void setNro_documento(Long nro_documento) {
        this.nro_documento = nro_documento;
    }

    public long getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(Long tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }
}
