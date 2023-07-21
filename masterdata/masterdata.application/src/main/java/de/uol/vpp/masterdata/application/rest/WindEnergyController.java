package de.uol.vpp.masterdata.application.rest;

import de.uol.vpp.masterdata.application.ApplicationDomainConverter;
import de.uol.vpp.masterdata.application.dto.WindEnergyDTO;
import de.uol.vpp.masterdata.application.payload.ApiResponse;
import de.uol.vpp.masterdata.domain.exceptions.ProducerException;
import de.uol.vpp.masterdata.domain.exceptions.ProducerServiceException;
import de.uol.vpp.masterdata.domain.services.IWindEnergyService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * REST-Ressource für Windkraftanlagen
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/wind", produces = MediaType.APPLICATION_JSON_VALUE)
public class WindEnergyController {

    private final IWindEnergyService service;
    private final ApplicationDomainConverter converter;

    /**
     * Hole alle Windkraftanlagen eines DK
     *
     * @param decentralizedPowerPlantId Id des DK
     * @return Liste Windkraftanlagen
     */
    @GetMapping(path = "/by/dpp/{" +
            "decentralizedPowerPlantId}")
    public ResponseEntity<?> getAllWindEnergysByDecentralizedPowerPlant(@PathVariable String decentralizedPowerPlantId) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse(true, false, "风力电站查询成功.",
                            service.getAllByDecentralizedPowerPlantId(decentralizedPowerPlantId)
                                    .stream()
                                    .map(converter::toApplication)
                                    .collect(Collectors.toList())
                    ), HttpStatus.OK);
        } catch (ProducerServiceException e) {
            return new ResponseEntity<>(new ApiResponse(false, false, e.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException sqlException) {
            return new ResponseEntity<>(new ApiResponse(
                    false, false, "发生数据完整性错误.", null
            ), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Hole alle Windkraftanlagen eines Haushalts
     *
     * @param householdId Id des Haushalts
     * @return Liste von Windkraftanlagen
     */
    @GetMapping(path = "/by/household/{" +
            "householdId}")
    public ResponseEntity<?> getAllWindEnergysByHousehold(@PathVariable String householdId) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse(true, false, "成功查询风力涡轮机.",
                            service.getAllByHouseholdId(householdId)
                                    .stream()
                                    .map(converter::toApplication)
                                    .collect(Collectors.toList())
                    ), HttpStatus.OK);
        } catch (ProducerServiceException e) {
            return new ResponseEntity<>(new ApiResponse(false, false, e.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException sqlException) {
            return new ResponseEntity<>(new ApiResponse(
                    false, false, "发生数据完整性错误.", null
            ), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Hole eine spezifische Windkraftanlage
     *
     * @param windEnergyId Id der Windkraftanlage
     * @return Windkraftanlage
     */
    @GetMapping(path = "/{windEnergyId}")
    public ResponseEntity<?> getOneWindEnergy(@PathVariable String windEnergyId) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse(true, false,
                            String.format("风力电站 %s 已成功查询.", windEnergyId), converter.toApplication(service.get(windEnergyId)))
                    , HttpStatus.OK);
        } catch (ProducerServiceException e) {
            return new ResponseEntity<>(new ApiResponse(
                    false, false, e.getMessage(), null
            ), HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException sqlException) {
            return new ResponseEntity<>(new ApiResponse(
                    false, false, "发生数据完整性错误.", null
            ), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Persistiert Windkraftanlage und weist es einem DK zu
     *
     * @param dto                       zu speichernde Windkraftanlage
     * @param decentralizedPowerPlantId Id des DK
     * @return ApiResponse ohne Daten
     */
    @PostMapping("/by/dpp/{decentralizedPowerPlantId}")
    public ResponseEntity<?> saveWindEnergyWithDecentralizedPowerPlant(@RequestBody WindEnergyDTO dto, @PathVariable String decentralizedPowerPlantId) {
        try {
            service.saveWithDecentralizedPowerPlant(converter.toDomain(dto), decentralizedPowerPlantId);
            return ResponseEntity.ok().body(new ApiResponse(
                    true, false,
                    String.format("风力电站 %s 已成功创建.", dto.getWindEnergyId()), null));
        } catch (ProducerServiceException | ProducerException e) {
            return new ResponseEntity<>(new ApiResponse(
                    false, false, e.getMessage(), null
            ), HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException sqlException) {
            return new ResponseEntity<>(new ApiResponse(
                    false, false, "发生数据完整性错误.", null
            ), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Persistiert Windkraftanlage und weist es einem Haushalt zu
     *
     * @param dto         zu speichernde Windkraftanlage
     * @param householdId Id des Haushalts
     * @return ApiResponse ohne Daten
     */
    @PostMapping("/by/household/{householdId}")
    public ResponseEntity<?> saveWindEnergyWithHousehold(@RequestBody WindEnergyDTO dto,
                                                         @PathVariable String householdId) {
        try {
            service.saveWithHousehold(converter.toDomain(dto), householdId);
            return ResponseEntity.ok().body(new ApiResponse(
                    true, false,
                    String.format("风力电站 %s 已成功创建.", dto.getWindEnergyId()), null));
        } catch (ProducerServiceException | ProducerException e) {
            return new ResponseEntity<>(new ApiResponse(
                    false, false, e.getMessage(), null
            ), HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException sqlException) {
            return new ResponseEntity<>(new ApiResponse(
                    false, false, "发生数据完整性错误.", null
            ), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Löscht Windkraftanlage
     *
     * @param windEnergyId        Id der Windkraftanlage
     * @param virtualPowerPlantId Id des VK
     * @return ApiResponse ohne Daten
     */
    @DeleteMapping(path = "/{windEnergyId}")
    public ResponseEntity<?> deleteWindEnergy(@PathVariable String windEnergyId, @RequestParam String virtualPowerPlantId) {
        try {
            service.delete(windEnergyId, virtualPowerPlantId);
            return ResponseEntity.ok().body(
                    new ApiResponse(true, false,
                            String.format("风力电站 %s 已成功删除.", windEnergyId), null));
        } catch (ProducerServiceException e) {
            return new ResponseEntity<>(new ApiResponse(
                    false, false, e.getMessage(), null
            ), HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException sqlException) {
            return new ResponseEntity<>(new ApiResponse(
                    false, false, "发生数据完整性错误.", null
            ), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Aktualisiert Windkraftanlage
     *
     * @param windEnergyId        Id der Windkraftanlage
     * @param newDto              aktualisierte Daten
     * @param virtualPowerPlantId Id des VK
     * @return ApiResponse ohne Daten
     */
    @PutMapping(path = "/{windEnergyId}")
    public ResponseEntity<?> updateWindEnergy(@PathVariable String windEnergyId, @RequestBody WindEnergyDTO newDto, @RequestParam String virtualPowerPlantId) {
        try {
            service.update(windEnergyId, converter.toDomain(newDto), virtualPowerPlantId);
            return ResponseEntity.ok().body(new ApiResponse(true, false,
                    String.format("风力电站 %s 已成功更新.", windEnergyId), null));
        } catch (ProducerServiceException | ProducerException e) {
            return new ResponseEntity<>(new ApiResponse(
                    false, false, e.getMessage(), null
            ), HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException sqlException) {
            return new ResponseEntity<>(new ApiResponse(
                    false, false, "发生数据完整性错误.", null
            ), HttpStatus.NOT_FOUND);
        }
    }


}
