package de.uol.vpp.masterdata.domain.exceptions;



public class StorageException extends Exception {

    private static final long serialVersionUID = 2503333955630375826L;

    public StorageException(String attribute) {
        super("验证" + "储能实体的属性" + attribute + "失败.");
    }

    public StorageException(String attribute, Throwable cause) {
        super("验证" + "储能实体的属性" + attribute + "失败.", cause);
    }
}
