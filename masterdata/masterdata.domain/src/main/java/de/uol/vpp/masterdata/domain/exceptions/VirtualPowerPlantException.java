package de.uol.vpp.masterdata.domain.exceptions;



public class VirtualPowerPlantException extends Exception {

    private static final long serialVersionUID = -3583381934285182159L;

    public VirtualPowerPlantException(String attribute) {
        super("验证" + "-虚拟电厂实体的属性" + attribute + "失败.");
    }

    public VirtualPowerPlantException(String attribute, Throwable cause) {
        super("验证" + "-虚拟电厂实体的属性" + attribute + "失败.", cause);
    }
}
