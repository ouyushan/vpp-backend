package de.uol.vpp.masterdata.infrastructure.repositories;

import de.uol.vpp.masterdata.domain.aggregates.DecentralizedPowerPlantAggregate;
import de.uol.vpp.masterdata.domain.aggregates.HouseholdAggregate;
import de.uol.vpp.masterdata.domain.entities.OtherEnergyEntity;
import de.uol.vpp.masterdata.domain.exceptions.ProducerException;
import de.uol.vpp.masterdata.domain.exceptions.ProducerRepositoryException;
import de.uol.vpp.masterdata.domain.repositories.IOtherEnergyRepository;
import de.uol.vpp.masterdata.domain.valueobjects.OtherEnergyIdVO;
import de.uol.vpp.masterdata.infrastructure.InfrastructureEntityConverter;
import de.uol.vpp.masterdata.infrastructure.entities.DecentralizedPowerPlant;
import de.uol.vpp.masterdata.infrastructure.entities.Household;
import de.uol.vpp.masterdata.infrastructure.entities.OtherEnergy;
import de.uol.vpp.masterdata.infrastructure.jpaRepositories.DecentralizedPowerPlantJpaRepository;
import de.uol.vpp.masterdata.infrastructure.jpaRepositories.HouseholdJpaRepository;
import de.uol.vpp.masterdata.infrastructure.jpaRepositories.OtherEnergyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementierung der Schnittstellendefinition {@link IOtherEnergyRepository}
 * 接口定义 {@link IOtherEnergyRepository} 的实现
 */
@RequiredArgsConstructor
@Service
public class OtherEnergyRepositoryImpl implements IOtherEnergyRepository {

    private final OtherEnergyJpaRepository jpaRepository;
    private final DecentralizedPowerPlantJpaRepository decentralizedPowerPlantJpaRepository;
    private final HouseholdJpaRepository householdJpaRepository;
    private final InfrastructureEntityConverter converter;

    @Override
    public List<OtherEnergyEntity> getAllByDecentralizedPowerPlant(DecentralizedPowerPlantAggregate decentralizedPowerPlantAggregate) throws ProducerRepositoryException {
        try {
            Optional<DecentralizedPowerPlant> dpp = decentralizedPowerPlantJpaRepository
                    .findOneById(decentralizedPowerPlantAggregate.getDecentralizedPowerPlantId().getValue());
            if (dpp.isPresent()) {
                List<OtherEnergyEntity> result = new ArrayList<>();
                for (OtherEnergy otherEnergy : jpaRepository.findAllByDecentralizedPowerPlant(dpp.get())) {
                    result.add(converter.toDomain(otherEnergy));
                }
                return result;
            } else {
                throw new ProducerRepositoryException(String.format("通过分布式电厂 %s 无法查询其它能源.", decentralizedPowerPlantAggregate.getDecentralizedPowerPlantId().getValue()));
            }
        } catch (ProducerException e) {
            throw new ProducerRepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public List<OtherEnergyEntity> getAllByHousehold(HouseholdAggregate householdAggregate) throws ProducerRepositoryException {
        try {
            Optional<Household> household = householdJpaRepository
                    .findOneById(householdAggregate.getHouseholdId().getValue());
            if (household.isPresent()) {
                List<OtherEnergyEntity> result = new ArrayList<>();
                for (OtherEnergy OtherEnergy : jpaRepository.findAllByHousehold(household.get())) {
                    result.add(converter.toDomain(OtherEnergy));
                }
                return result;
            } else {
                throw new ProducerRepositoryException(String.format("通过住户信息 %s 无法查询其它能源.", householdAggregate.getHouseholdId().getValue()));
            }
        } catch (ProducerException e) {
            throw new ProducerRepositoryException(e.getMessage(), e);
        }

    }

    @Override
    public Optional<OtherEnergyEntity> getById(OtherEnergyIdVO id) throws ProducerRepositoryException {
        try {
            Optional<OtherEnergy> result = jpaRepository.findOneById(id.getValue());
            if (result.isPresent()) {
                return Optional.of(converter.toDomain(result.get()));
            }
            return Optional.empty();
        } catch (ProducerException e) {
            throw new ProducerRepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public void save(OtherEnergyEntity OtherEnergyEntity) throws ProducerRepositoryException {
        OtherEnergy jpaEntity = converter.toInfrastructure(OtherEnergyEntity);
        jpaRepository.save(jpaEntity);
    }

    @Override
    public void assignToDecentralizedPowerPlant(OtherEnergyEntity otherEnergyEntity, DecentralizedPowerPlantAggregate decentralizedPowerPlantAggregate) throws ProducerRepositoryException {
        Optional<DecentralizedPowerPlant> dpp = decentralizedPowerPlantJpaRepository.findOneById(decentralizedPowerPlantAggregate.getDecentralizedPowerPlantId().getValue());
        if (dpp.isPresent()) {
            Optional<OtherEnergy> other = jpaRepository.findOneById(otherEnergyEntity.getId().getValue());
            if (other.isPresent()) {
                if (other.get().getDecentralizedPowerPlant() == null &&
                        other.get().getHousehold() == null) {
                    other.get().setDecentralizedPowerPlant(dpp.get());
                    jpaRepository.save(other.get());
                    dpp.get().getOthers().add(other.get());
                    decentralizedPowerPlantJpaRepository.save(dpp.get());
                } else {
                    throw new ProducerRepositoryException(
                            String.format("其它能源 %s 无法分配给分布式电厂，因为已分配给一个实体.", otherEnergyEntity.getId().getValue())
                    );
                }
            } else {
                throw new ProducerRepositoryException(
                        String.format("其它能源 %s 找不到.", otherEnergyEntity.getId().getValue())
                );
            }
        } else {
            throw new ProducerRepositoryException(
                    String.format("分布式电厂 %s 无法找到其它能源进行分配.", decentralizedPowerPlantAggregate.getDecentralizedPowerPlantId().getValue())
            );
        }
    }

    @Override
    public void assignToHousehold(OtherEnergyEntity otherEnergyEntity, HouseholdAggregate householdAggregate) throws ProducerRepositoryException {
        Optional<Household> household = householdJpaRepository.findOneById(householdAggregate.getHouseholdId().getValue());
        if (household.isPresent()) {
            Optional<OtherEnergy> OtherEnergy = jpaRepository.findOneById(otherEnergyEntity.getId().getValue());
            if (OtherEnergy.isPresent()) {
                if (OtherEnergy.get().getDecentralizedPowerPlant() == null &&
                        OtherEnergy.get().getHousehold() == null) {
                    OtherEnergy.get().setHousehold(household.get());
                    jpaRepository.save(OtherEnergy.get());
                    household.get().getOthers().add(OtherEnergy.get());
                    householdJpaRepository.save(household.get());
                } else {
                    throw new ProducerRepositoryException(
                            String.format("新能源电厂 %s 无法分配到住户中, 新能源电厂 已分配给实体.", otherEnergyEntity.getId().getValue())
                    );
                }
            } else {
                throw new ProducerRepositoryException(
                        String.format("新能源电厂 %s 找不到.", otherEnergyEntity.getId().getValue())
                );
            }
        } else {
            throw new ProducerRepositoryException(
                    String.format("分布式电厂 %s 无法找到分布式电厂的分配.", householdAggregate.getHouseholdId().getValue())
            );
        }
    }

    @Override
    public void deleteById(OtherEnergyIdVO id) throws ProducerRepositoryException {
        Optional<OtherEnergy> jpaEntity = jpaRepository.findOneById(id.getValue());
        if (jpaEntity.isPresent()) {
            jpaRepository.delete(jpaEntity.get());
        } else {
            throw new ProducerRepositoryException(
                    String.format("新能源电厂 %s 无法删除, 因为没有查到新能源电厂.", id.getValue())
            );
        }
    }

    @Override
    public void update(OtherEnergyIdVO id, OtherEnergyEntity domainEntity) throws ProducerRepositoryException {
        Optional<OtherEnergy> jpaEntityOptional = jpaRepository.findOneById(id.getValue());
        if (jpaEntityOptional.isPresent()) {
            OtherEnergy jpaEntity = jpaEntityOptional.get();
            OtherEnergy updated = converter.toInfrastructure(domainEntity);
            jpaEntity.setId(updated.getId());
            jpaEntity.setRatedCapacity(updated.getRatedCapacity());
            jpaEntity.setCapacity(updated.getCapacity());
            jpaRepository.save(jpaEntity);
        } else {
            throw new ProducerRepositoryException("Die alternative Erzeugungsanlage konnte nicht aktualisiert werden, da die alternative Erzeugungsanlage nicht gefunden wurde.");
        }
    }

}
