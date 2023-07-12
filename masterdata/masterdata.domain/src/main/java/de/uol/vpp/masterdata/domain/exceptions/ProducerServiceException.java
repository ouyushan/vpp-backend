package de.uol.vpp.masterdata.domain.exceptions;

import java.io.Serial;

public class ProducerServiceException extends Exception {

    private static final long serialVersionUID = -6032334463103602756L;

    public ProducerServiceException(String message) {
        super(message);
    }

    public ProducerServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
