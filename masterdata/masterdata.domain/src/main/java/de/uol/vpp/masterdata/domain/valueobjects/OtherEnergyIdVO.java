package de.uol.vpp.masterdata.domain.valueobjects;

import de.uol.vpp.masterdata.domain.exceptions.ProducerException;
import lombok.Getter;

/**
 * Ein Value Object ist für die Validierung der Attribute zuständig
 * Für eine Definition des Objektes siehe {@link de.uol.vpp.masterdata.domain.entities.OtherEnergyEntity}
 *
 * 值对象负责验证属性 有关对象的定义，请参阅 {@link de.uol.vpp.masterdata.domain.entities.OtherEnergyEntity}
 */
@Getter
public class OtherEnergyIdVO {
    private String value;

    public OtherEnergyIdVO(String value) throws ProducerException {
        if (value == null || value.isEmpty() || value.isBlank()) {
            throw new ProducerException("otherEnergyId", "alternative Erzeugungsanlage");
        }
        this.value = value.toUpperCase();
    }
}
