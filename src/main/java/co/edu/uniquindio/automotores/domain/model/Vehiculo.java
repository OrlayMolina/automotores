package co.edu.uniquindio.automotores.domain.model;

public class Vehiculo {
    private String nro_placa;
    private Long tipo_vehiculo;
    private String marca;
    private String modelo;
    private String anio_moodelo;
    private Long nro_motor;

    public Vehiculo() {
    }

    public String getNro_placa() {
        return nro_placa;
    }

    public void setNro_placa(String nro_placa) {
        this.nro_placa = nro_placa;
    }

    public Long getTipo_vehivulo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehivulo(Long tipo_vehivulo) {
        this.tipo_vehiculo = tipo_vehivulo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio_moodelo() {
        return anio_moodelo;
    }

    public void setAnio_moodelo(String anio_moodelo) {
        this.anio_moodelo = anio_moodelo;
    }

    public Long getNro_motor() {
        return nro_motor;
    }

    public void setNro_motor(Long nro_motor) {
        this.nro_motor = nro_motor;
    }
}
