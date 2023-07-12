package de.uol.vpp.masterdata.domain.exceptions;

import java.io.Serial;

public class DecentralizedPowerPlantServiceException extends Exception {

    private static final long serialVersionUID = -1258118781567700294L;

    public DecentralizedPowerPlantServiceException(String message) {
        super(message);
    }

    public DecentralizedPowerPlantServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
