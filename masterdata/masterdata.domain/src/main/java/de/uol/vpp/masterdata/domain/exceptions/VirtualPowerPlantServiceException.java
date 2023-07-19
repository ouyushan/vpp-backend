package de.uol.vpp.masterdata.domain.exceptions;



public class VirtualPowerPlantServiceException extends Exception {

    private static final long serialVersionUID = 819924033799674351L;

    public VirtualPowerPlantServiceException(String message) {
        super(message);
    }

    public VirtualPowerPlantServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
