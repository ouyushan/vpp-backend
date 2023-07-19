package de.uol.vpp.masterdata.domain.exceptions;



public class DecentralizedPowerPlantException extends Exception {

    private static final long serialVersionUID = 6384268579313299259L;

    public DecentralizedPowerPlantException(String attribute) {
        super("Validierung des " + attribute + "-Attributs der Entität DK ist fehlgeschlagen.");
    }

    public DecentralizedPowerPlantException(String attribute, Throwable cause) {
        super("Validierung des " + attribute + "-Attributs der Entität DK ist fehlgeschlagen.", cause);
    }
}
