package co.edu.uniquindio.automotores.application.exceptions;

public class SesionNoIniciadaException extends RuntimeException{

    public SesionNoIniciadaException(String message) {
        super(message);
    }

    public SesionNoIniciadaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
