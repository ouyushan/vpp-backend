package de.uol.vpp.masterdata.domain.valueobjects;

import de.uol.vpp.masterdata.domain.exceptions.ProducerException;
import lombok.Getter;
import lombok.Setter;

/**
 * Ein Value Object ist für die Validierung der Attribute zuständig
 * Für eine Definition des Objektes siehe {@link de.uol.vpp.masterdata.domain.entities.WindEnergyEntity}
 *
 *  * 值对象负责验证属性 有关对象的定义，请参阅 {@link de.uol.vpp.masterdata.domain.entities.WindEnergyEntity}
 */
@Getter
@Setter
public class WindEnergyLatitudeVO {

    private Double value;

    public WindEnergyLatitudeVO(Double value) throws ProducerException {
        if (value == null || value < -90. || value > 90.) {
            throw new ProducerException("latitude", "Windkraftanlage");
        }
        this.value = value;
    }
}
