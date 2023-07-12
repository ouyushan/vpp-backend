package de.uol.vpp.masterdata.domain.exceptions;

import java.io.Serial;

public class HouseholdRepositoryException extends Exception {

    private static final long serialVersionUID = 4388109561190150234L;

    public HouseholdRepositoryException(String message) {
        super(message);
    }

    public HouseholdRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
