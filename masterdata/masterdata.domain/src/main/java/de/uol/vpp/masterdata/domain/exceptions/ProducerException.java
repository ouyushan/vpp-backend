package de.uol.vpp.masterdata.domain.exceptions;


public class ProducerException extends Exception {
    private static final long serialVersionUID = 5309520306131206230L;

    public ProducerException(String attribute, String entity) {
        super("验证" + entity + "-实体的属性" + attribute + "失败.");
    }

    public ProducerException(String attribute, String entity, Throwable cause) {
        super("验证" + entity + "-实体的属性" + attribute + "失败.", cause);
    }
}
