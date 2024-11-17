package co.edu.uniquindio.automotores.domain.model;

public class Empleado {
    private Long nro_documento;
    private Long tipo_documento;
    private String telefono;
    private Long Cargo;
    private Float salario;
    private String primer_nombre;
    private String segundo_nombre;
    private String primer_apellido;
    private String segundo_apellido;

    public Empleado() {
    }

    public Empleado(Long nro_documento, Long tipo_documento, String telefono, Long cargo, Float salario, String primer_nombre, String segundo_nombre, String primer_apellido, String segundo_apellido) {
        this.nro_documento = nro_documento;
        this.tipo_documento = tipo_documento;
        this.telefono = telefono;
        Cargo = cargo;
        this.salario = salario;
        this.primer_nombre = primer_nombre;
        this.segundo_nombre = segundo_nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
    }

    public Long getNro_documento() {
        return nro_documento;
    }

    public void setNro_documento(Long nro_documento) {
        this.nro_documento = nro_documento;
    }

    public Long getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(Long tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getCargo() {
        return Cargo;
    }

    public void setCargo(Long cargo) {
        Cargo = cargo;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public String getPrimer_nombre() {
        return primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getSegundo_nombre() {
        return segundo_nombre;
    }

    public void setSegundo_nombre(String segundo_nombre) {
        this.segundo_nombre = segundo_nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }
}
