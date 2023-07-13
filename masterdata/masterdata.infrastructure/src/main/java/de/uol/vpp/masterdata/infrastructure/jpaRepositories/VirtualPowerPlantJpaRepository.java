package de.uol.vpp.masterdata.infrastructure.jpaRepositories;

import de.uol.vpp.masterdata.infrastructure.entities.VirtualPowerPlant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * VK JPA Repository für die Kommunikation mit der Datenbank
 * 用于与数据库通信的 VK JPA 存储库
 */
public interface VirtualPowerPlantJpaRepository extends JpaRepository<VirtualPowerPlant, Long> {
    /**
     * Holt spezifisches VK
     *
     * @param virtualPowerPlantId Id des VK
     * @return VK
     */
    Optional<VirtualPowerPlant> findOneById(String virtualPowerPlantId);
}
