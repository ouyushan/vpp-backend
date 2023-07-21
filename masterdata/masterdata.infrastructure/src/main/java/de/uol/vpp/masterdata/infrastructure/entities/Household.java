package de.uol.vpp.masterdata.infrastructure.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Datenbank-Entität der Haushalte {@link de.uol.vpp.masterdata.domain.aggregates.HouseholdAggregate}
 * 住户数据库实体 {@link de.uol.vpp.masterdata.domain.aggregates.HouseholdAggregate}
 */
@Entity
@Data
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalId;

    @Column(unique = true, nullable = false)
    private String id;

    @OneToMany(mappedBy = "household")
    private List<WindEnergy> winds;

    @OneToMany(mappedBy = "household")
    private List<WaterEnergy> waters;

    @OneToMany(mappedBy = "household")
    private List<SolarEnergy> solars;

    @OneToMany(mappedBy = "household")
    private List<OtherEnergy> others;

    @OneToMany(mappedBy = "household")
    private List<Storage> storages;

    @Column(nullable = false)
    private Integer memberAmount;

    @ManyToOne
    @JoinColumn(name = "virtual_power_plant_id")
    private VirtualPowerPlant virtualPowerPlant;


}
