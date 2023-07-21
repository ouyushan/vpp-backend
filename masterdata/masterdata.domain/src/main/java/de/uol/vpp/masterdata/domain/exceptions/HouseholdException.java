package de.uol.vpp.masterdata.domain.exceptions;



public class HouseholdException extends Exception {

    private static final long serialVersionUID = -8591035060712897986L;

    public HouseholdException(String attribute) {
        super("验证" + "住户实体的属性" + attribute + "失败.");
    }

    public HouseholdException(String attribute, Throwable cause) {
        super("验证" + "住户实体的属性" + attribute + "失败.", cause);
    }
}
