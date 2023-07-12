package de.uol.vpp.masterdata.domain.utils;

import de.uol.vpp.masterdata.domain.valueobjects.*;

/**
 * Diese Schnittstellendefinition beschreibt die Funktionen, die die Editierbarkeit von Komponenten eine VK prüft.
 * Die Komponenten eines VK, sind nämlich nur editierbar, wenn das VK nicht veröffentlicht ist.
 *
 * 此接口定义描述了 VK 检查组件可编辑性的函数。仅当 VK 未发布时，VK 的组件才可编辑。
 */
public interface IPublishUtil {

    boolean isEditable(VirtualPowerPlantIdVO virtualPowerPlantId, DecentralizedPowerPlantIdVO decentralizedPowerPlantId) throws PublishException;

    boolean isEditable(VirtualPowerPlantIdVO virtualPowerPlantId, HouseholdIdVO householdId) throws PublishException;

    boolean isEditable(VirtualPowerPlantIdVO virtualPowerPlantId, WindEnergyIdVO windEnergyId) throws PublishException;

    boolean isEditable(VirtualPowerPlantIdVO virtualPowerPlantId, WaterEnergyIdVO waterEnergyId) throws PublishException;

    boolean isEditable(VirtualPowerPlantIdVO virtualPowerPlantId, SolarEnergyIdVO solarEnergyId) throws PublishException;

    boolean isEditable(VirtualPowerPlantIdVO virtualPowerPlantId, OtherEnergyIdVO otherEnergyId) throws PublishException;

    boolean isEditable(VirtualPowerPlantIdVO virtualPowerPlantId, StorageIdVO storageId) throws PublishException;

}
