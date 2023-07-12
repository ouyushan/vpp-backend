package de.uol.vpp.masterdata.domain.valueobjects;

import lombok.Getter;
import lombok.Setter;

/**
 * Ein Value Object ist für die Validierung der Attribute zuständig
 * Für eine Definition des Objektes siehe {@link de.uol.vpp.masterdata.domain.aggregates.VirtualPowerPlantAggregate}
 *
 * 值对象负责验证属性 有关对象的定义，请参阅 {@link de.uol.vpp.masterdata.domain.aggregates.VirtualPowerPlantAggregate}
 */
@Setter
@Getter
public class VirtualPowerPlantPublishedVO {


    private final boolean value;

    public VirtualPowerPlantPublishedVO(boolean value) {
        this.value = value;
    }

}
