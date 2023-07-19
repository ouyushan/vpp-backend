package de.uol.vpp.production.domain.entities;

import de.uol.vpp.production.domain.valueobjects.*;
import lombok.Data;

/**
 * Domänen-Aggregat für den Erzeugungswert einer Erzeugungsanlage
 * Dieses Aggregat beinhaltet die Erzeugungsanlage mit dem jeweiligen Typ, sowie Informationen
 * über die im aktuellen Zeitpunkt höchstmögliche Erzeugung, sowie die aktuelle/tatsächliche Erzeugung
 * 发电厂发电值的域聚合 此聚合包含具有相应类型的发电厂，以及有关当前时间可能的最高发电量以及当前实际发电量的信息
 */
@Data
public class ProductionProducerEntity {
    /**
     * Erzeugung ist einer Erzeugungsanlage zugewiesen
     */
    private ProductionProducerIdVO producerId;
    /**
     * Typ der Erzeugungsanlage (z.B. WIND)
     */
    private ProductionProducerTypeVO productionType;
    /**
     * Aktueller Zeitpunkt
     */
    private ProductionProducerStartTimestampVO startTimestamp;
    /**
     * Tatsächlicher Erzeugungswert zum Zeitpunkt {@link ProductionProducerEntity#startTimestamp}
     */
    private ProductionProducerCurrentValueVO currentValue;
    /**
     * Höchstmöglicher Erzeugungswert zum Zeitpunkt {@link ProductionProducerEntity#startTimestamp}
     * bei einer Kapazität von 100%
     */
    private ProductionProducerPossibleValueVO possibleValue;
}
