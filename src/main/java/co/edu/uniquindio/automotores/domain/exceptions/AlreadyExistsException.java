package co.edu.uniquindio.automotores.domain.exceptions;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
