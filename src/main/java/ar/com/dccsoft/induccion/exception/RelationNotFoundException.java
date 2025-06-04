package ar.com.dccsoft.induccion.exception;

public class RelationNotFoundException extends DomainException {

    public RelationNotFoundException() {
        super("No se encontro la relacion");
    }

}
