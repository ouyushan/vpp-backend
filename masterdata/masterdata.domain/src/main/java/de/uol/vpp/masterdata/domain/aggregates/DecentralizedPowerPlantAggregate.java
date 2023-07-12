package de.uol.vpp.masterdata.domain.aggregates;

import de.uol.vpp.masterdata.domain.aggregates.abstracts.DomainHasProducersAndStorages;
import de.uol.vpp.masterdata.domain.valueobjects.DecentralizedPowerPlantIdVO;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Domänen-Aggregat für dezentralte Kraftwerke
 * Erweitert abstrakte Klasse {@link DomainHasProducersAndStorages}
 *
 * 分布式发电厂的域聚合 扩展抽象类 {@link DomainHasProducersAndStorages}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DecentralizedPowerPlantAggregate extends DomainHasProducersAndStorages {
    /**
     * Identifizierung des DK
     */
    private DecentralizedPowerPlantIdVO decentralizedPowerPlantId;
}
