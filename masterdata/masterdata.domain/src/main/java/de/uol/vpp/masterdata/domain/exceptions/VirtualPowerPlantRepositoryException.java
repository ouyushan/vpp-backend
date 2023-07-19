package de.uol.vpp.masterdata.domain.exceptions;



public class VirtualPowerPlantRepositoryException extends Exception {

    private static final long serialVersionUID = -6147263447028523076L;

    public VirtualPowerPlantRepositoryException(String message) {
        super(message);
    }

    public VirtualPowerPlantRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
