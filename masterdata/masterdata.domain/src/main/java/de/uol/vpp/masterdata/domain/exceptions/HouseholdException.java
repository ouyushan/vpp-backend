package de.uol.vpp.masterdata.domain.exceptions;

import java.io.Serial;

public class HouseholdException extends Exception {

    private static final long serialVersionUID = -8591035060712897986L;

    public HouseholdException(String attribute) {
        super("Validierung des " + attribute + "-Attributs der Entität Haushalt ist fehlgeschlagen.");
    }

    public HouseholdException(String attribute, Throwable cause) {
        super("Validierung des " + attribute + "-Attributs der Entität Haushalt ist fehlgeschlagen.", cause);
    }
}
