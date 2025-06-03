package ar.com.dccsoft.induccion.exception;


public abstract class DomainException extends RuntimeException {

    public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
    }
}
