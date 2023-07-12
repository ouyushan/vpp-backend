package de.uol.vpp.masterdata.domain.valueobjects;

import de.uol.vpp.masterdata.domain.exceptions.HouseholdException;
import lombok.Getter;
import lombok.Setter;

/**
 * Ein Value Object ist für die Validierung der Attribute zuständig
 * Für eine Definition des Objektes siehe {@link de.uol.vpp.masterdata.domain.aggregates.HouseholdAggregate}
 *
 * 值对象负责验证属性 有关对象的定义，请参阅 {@link de.uol.vpp.masterdata.domain.aggregates.HouseholdAggregate}
 */
@Getter
@Setter
public class HouseholdMemberAmountVO {

    private final Integer value;

    public HouseholdMemberAmountVO(Integer value) throws HouseholdException {
        if (value == null || value < 0) {
            throw new HouseholdException("householdMemberAmount");
        }
        this.value = value;
    }
}
