package ar.com.dccsoft.induccion.exception;

public class InvalidFormException extends DomainException {

    public InvalidFormException() {
        super("El formulario es invalido");
    }

}
