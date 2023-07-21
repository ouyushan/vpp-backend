package de.uol.vpp.production.domain.exceptions;

public class ProductionException extends Exception {
    public ProductionException(String attribute, String entity) {
        super("验证" + entity + "-实体的属性" + attribute + "失败.");
    }

    public ProductionException(String attribute, String entity, Throwable cause) {
        super("验证" + entity + "-实体的属性" + attribute + "失败.", cause);
    }
}
