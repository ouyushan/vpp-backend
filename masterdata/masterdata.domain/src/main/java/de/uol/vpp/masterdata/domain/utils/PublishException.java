package de.uol.vpp.masterdata.domain.utils;



public class PublishException extends Exception {

    private static final long serialVersionUID = -825544246092709585L;

    public PublishException(String message) {
        super(message);
    }

    public PublishException(String message, Throwable cause) {
        super(message, cause);
    }
}
