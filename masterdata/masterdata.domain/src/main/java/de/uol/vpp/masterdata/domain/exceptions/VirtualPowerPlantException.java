package de.uol.vpp.masterdata.domain.exceptions;



public class VirtualPowerPlantException extends Exception {

    private static final long serialVersionUID = -3583381934285182159L;

    public VirtualPowerPlantException(String attribute) {
        super("Validierung des " + attribute + "-Attributs der Entität VK ist fehlgeschlagen.");
    }

    public VirtualPowerPlantException(String attribute, Throwable cause) {
        super("Validierung des " + attribute + "-Attributs der Entität VK ist fehlgeschlagen.", cause);
    }
}
