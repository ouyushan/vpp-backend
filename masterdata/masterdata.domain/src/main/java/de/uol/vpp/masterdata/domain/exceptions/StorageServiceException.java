package de.uol.vpp.masterdata.domain.exceptions;

import java.io.Serial;

public class StorageServiceException extends Exception {

    private static final long serialVersionUID = 799628630850728457L;

    public StorageServiceException(String message) {
        super(message);
    }

    public StorageServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
