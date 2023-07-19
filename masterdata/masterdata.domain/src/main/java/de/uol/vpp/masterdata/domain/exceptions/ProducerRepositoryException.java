package de.uol.vpp.masterdata.domain.exceptions;



public class ProducerRepositoryException extends Exception {

    private static final long serialVersionUID = 4389945864938353853L;

    public ProducerRepositoryException(String message) {
        super(message);
    }

    public ProducerRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
