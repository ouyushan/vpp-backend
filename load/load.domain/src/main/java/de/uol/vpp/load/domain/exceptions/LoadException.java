package de.uol.vpp.load.domain.exceptions;

public class LoadException extends Exception {

    public LoadException(String attribute, String entity) {
        super("验证" + entity + "-实体的属性" + attribute + "失败.");
    }

    public LoadException(String attribute, String entity, Throwable cause) {
        super("验证" + entity + "-实体的属性" + attribute + "失败.", cause);
    }
}
