package de.uol.vpp.masterdata.domain.valueobjects;

import de.uol.vpp.masterdata.domain.exceptions.ProducerException;
import lombok.Getter;
import lombok.Setter;

/**
 * Ein Value Object ist für die Validierung der Attribute zuständig
 * Für eine Definition des Objektes siehe {@link de.uol.vpp.masterdata.domain.entities.WaterEnergyEntity}
 *
 * 值对象负责验证属性 有关对象的定义，请参阅 {@link de.uol.vpp.masterdata.domain.entities.WindEnergyEntity}
 */
@Getter
@Setter
public class WaterEnergyVolumeFlowVO {
    private Double value;

    public WaterEnergyVolumeFlowVO(Double value) throws ProducerException {
        if (value == null || value < 0) {
            throw new ProducerException("volumeFlow", "Wasserkraftanlage");
        }
        this.value = Math.round(1000.0 * value) / 1000.0;
    }
}
