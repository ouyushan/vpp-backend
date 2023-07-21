package de.uol.vpp.masterdata.domain.aggregates;

import de.uol.vpp.masterdata.domain.aggregates.abstracts.DomainHasProducersAndStorages;
import de.uol.vpp.masterdata.domain.valueobjects.HouseholdIdVO;
import de.uol.vpp.masterdata.domain.valueobjects.HouseholdMemberAmountVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Domänen-Aggregat für Haushalte
 * Erweitert abstrakte Klasse {@link DomainHasProducersAndStorages}
 *
 * 住户的域聚合 扩展抽象类 {@link DomainHasProducersAndStorages}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HouseholdAggregate extends DomainHasProducersAndStorages {
    /**
     * Identifizierung des Haushalts
     * 住户身份证明
     */
    private HouseholdIdVO householdId;
    /**
     * Anzahl der Haushaltsmitglieder
     * 住户成员人数
     */
    private HouseholdMemberAmountVO householdMemberAmount;
}
