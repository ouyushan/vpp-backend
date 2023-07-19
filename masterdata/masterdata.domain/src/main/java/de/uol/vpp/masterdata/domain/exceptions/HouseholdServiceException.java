package de.uol.vpp.masterdata.domain.exceptions;



public class HouseholdServiceException extends Exception {

    private static final long serialVersionUID = 2437700852974950094L;

    public HouseholdServiceException(String message) {
        super(message);
    }

    public HouseholdServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
