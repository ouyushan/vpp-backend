package de.uol.vpp.action.domain.valueobjects;

import de.uol.vpp.action.domain.exceptions.ManipulationException;
import lombok.Data;

/**
 * Siehe {@link de.uol.vpp.action.domain.entities.ProducerManipulationEntity}
 */
@Data
public class ProducerManipulationProducerIdVO {
    private String value;

    public ProducerManipulationProducerIdVO(String value) throws ManipulationException {
        if (value == null || value.isBlank() || value.isEmpty()) {
            throw new ManipulationException("producerId", "发电操作");
        }
        this.value = value.toUpperCase();
    }
}
