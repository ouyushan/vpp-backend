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
 * 家庭的域聚合 扩展抽象类 {@link DomainHasProducersAndStorages}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HouseholdAggregate extends DomainHasProducersAndStorages {
    /**
     * Identifizierung des Haushalts
     * 家庭身份证明
     */
    private HouseholdIdVO householdId;
    /**
     * Anzahl der Haushaltsmitglieder
     * 家庭成员人数
     */
    private HouseholdMemberAmountVO householdMemberAmount;
}
