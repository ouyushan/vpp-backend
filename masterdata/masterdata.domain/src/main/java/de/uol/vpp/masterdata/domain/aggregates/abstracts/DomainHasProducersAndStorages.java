package de.uol.vpp.masterdata.domain.aggregates.abstracts;

import de.uol.vpp.masterdata.domain.entities.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstrakte Klasse für Haushalte und DK
 * Enthält die Speicher- und Erzeugungsanlagen
 *
 * 家庭和DK的抽象类 包括储能和发电设施
 */
@Data
public abstract class DomainHasProducersAndStorages {
    /**
     * Solaranlagen
     * 太阳能电池板
     */
    private List<SolarEnergyEntity> solars = new ArrayList<>();
    /**
     * Windkraftanlagen
     * 风力涡轮机
     */
    private List<WindEnergyEntity> winds = new ArrayList<>();
    /**
     * Wasserkraftanlagen
     * 水力发电厂
     */
    private List<WaterEnergyEntity> waters = new ArrayList<>();
    /**
     * alternative Erzeugungsanlagen mit konstanter Leistung
     * 具有恒定输出的替代发电厂
     */
    private List<OtherEnergyEntity> others = new ArrayList<>();
    /**
     * Speicheranlagen
     * 储能
     */
    private List<StorageEntity> storages = new ArrayList<>();
}
