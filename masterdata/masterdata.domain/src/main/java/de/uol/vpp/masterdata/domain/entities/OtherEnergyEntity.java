package de.uol.vpp.masterdata.domain.entities;

import de.uol.vpp.masterdata.domain.valueobjects.OtherEnergyCapacityVO;
import de.uol.vpp.masterdata.domain.valueobjects.OtherEnergyIdVO;
import de.uol.vpp.masterdata.domain.valueobjects.OtherEnergyRatedCapacityVO;
import lombok.Data;

/**
 * Domain-Entität einer alternativen Erzeugungsanlage mit konstanter Leistung
 *
 * 具有恒定功率的替代发电厂的域实体
 */
@Data
public class OtherEnergyEntity {
    /**
     * Identifizierung der Erzeugungsanlage
     */
    private OtherEnergyIdVO id;
    /**
     * Nennleistung in kW
     */
    private OtherEnergyRatedCapacityVO ratedCapacity;
    /**
     * Kapazität der Anlage, mit wie viel %-Leistung die Erzeugungsanlage läuft
     * Eine Kapazität von 0% bedeutet, dass die Anlage nicht läuft
     *
     * 工厂的容量，在发电厂运行的功率百分比下 容量为 0% 表示工厂未运行
     */
    private OtherEnergyCapacityVO capacity;
}
