package de.uol.vpp.masterdata.domain.exceptions;



public class StorageException extends Exception {

    private static final long serialVersionUID = 2503333955630375826L;

    public StorageException(String attribute) {
        super("Validierung des " + attribute + "-Attributs der Entität Storage ist fehlgeschlagen.");
    }

    public StorageException(String attribute, Throwable cause) {
        super("Validierung des " + attribute + "-Attributs der Entität Storage ist fehlgeschlagen.", cause);
    }
}
