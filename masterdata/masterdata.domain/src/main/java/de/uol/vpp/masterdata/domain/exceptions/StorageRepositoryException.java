package de.uol.vpp.masterdata.domain.exceptions;

import java.io.Serial;

public class StorageRepositoryException extends Exception {

    private static final long serialVersionUID = 5576568252252166421L;

    public StorageRepositoryException(String message) {
        super(message);
    }

    public StorageRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
