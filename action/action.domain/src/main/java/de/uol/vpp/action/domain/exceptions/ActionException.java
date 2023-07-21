package de.uol.vpp.action.domain.exceptions;

public class ActionException extends Exception {

    public ActionException(String attribute, String entity) {
        super("验证" + entity + "-实体的属性 " + attribute + "失败.");
    }

    public ActionException(String attribute, String entity, Throwable cause) {
        super("验证" + entity + "-实体的属性 " + attribute + "失败.", cause);
    }
}
