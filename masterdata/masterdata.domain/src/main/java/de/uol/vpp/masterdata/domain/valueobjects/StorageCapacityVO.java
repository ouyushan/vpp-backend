package de.uol.vpp.masterdata.domain.valueobjects;

import de.uol.vpp.masterdata.domain.exceptions.StorageException;
import lombok.Getter;
import lombok.Setter;

/**
 * Ein Value Object ist für die Validierung der Attribute zuständig
 * Für eine Definition des Objektes siehe {@link de.uol.vpp.masterdata.domain.entities.StorageEntity}
 *
 * 值对象负责验证属性 有关对象的定义，请参阅 {@link de.uol.vpp.masterdata.domain.entities.StorageEntity}
 */
@Getter
@Setter
public class StorageCapacityVO {

    private final Double value;

    public StorageCapacityVO(Double value) throws StorageException {
        if (value == null || value < 0 || value > 100) {
            throw new StorageException("capacity");
        }
        this.value = Math.round(1000.0 * value) / 1000.0;
    }
}
