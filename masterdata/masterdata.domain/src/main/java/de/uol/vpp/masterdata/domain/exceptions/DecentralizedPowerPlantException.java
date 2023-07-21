package de.uol.vpp.masterdata.domain.exceptions;



public class DecentralizedPowerPlantException extends Exception {

    private static final long serialVersionUID = 6384268579313299259L;

    public DecentralizedPowerPlantException(String attribute) {
        super("验证分布式电厂实体的属性" + attribute + "失败.");
    }

    public DecentralizedPowerPlantException(String attribute, Throwable cause) {
        super("验证分布式电厂实体的属性" + attribute + "失败.", cause);
    }
}
