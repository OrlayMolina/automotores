package co.edu.uniquindio.automotores.domain.model;

public class Usuario {

    private Long id;
    private Long id_empleado;
    private String correo;
    private String password;
    private String estado;

    public Usuario(){

    }

    public Usuario(Long id_empleado, String correo, String password, String estado) {
        this.id_empleado = id_empleado;
        this.correo = correo;
        this.password = password;
        this.estado = estado;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getId_empleado() {

        return id_empleado;
    }

    public void setId_empleado(Long id_empleado) {

        this.id_empleado = id_empleado;
    }

    public String getCorreo() {

        return correo;
    }

    public void setCorreo(String correo) {

        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
