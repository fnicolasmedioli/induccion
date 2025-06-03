package ar.com.dccsoft.induccion.exception;

public class UserAlreadyExistsException extends DomainException {

    public UserAlreadyExistsException() {
        super("El usuario ya existe");
    }

}
