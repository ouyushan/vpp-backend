package de.uol.vpp.masterdata.domain.exceptions;

import java.io.Serial;

public class ProducerException extends Exception {
    private static final long serialVersionUID = 5309520306131206230L;

    public ProducerException(String attribute, String entity) {
        super("Validierung des " + attribute + "-Attributs der Entität " + entity + " ist fehlgeschlagen.");
    }

    public ProducerException(String attribute, String entity, Throwable cause) {
        super("Validierung des " + attribute + "-Attributs der Entität " + entity + " ist fehlgeschlagen.", cause);
    }
}
