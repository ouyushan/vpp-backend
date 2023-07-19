package de.uol.vpp.masterdata.domain.exceptions;



public class DecentralizedPowerPlantRepositoryException extends Exception {

    private static final long serialVersionUID = -2364914433587388582L;

    public DecentralizedPowerPlantRepositoryException(String message) {
        super(message);
    }

    public DecentralizedPowerPlantRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
