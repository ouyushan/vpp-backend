package de.uol.vpp.masterdata.application.rest;

import de.uol.vpp.masterdata.application.ApplicationDomainConverter;
import de.uol.vpp.masterdata.application.dto.VirtualPowerPlantDTO;
import de.uol.vpp.masterdata.application.payload.ApiResponse;
import de.uol.vpp.masterdata.domain.exceptions.VirtualPowerPlantException;
import de.uol.vpp.masterdata.domain.exceptions.VirtualPowerPlantServiceException;
import de.uol.vpp.masterdata.domain.services.IVirtualPowerPlantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping(path = "/vpp", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VirtualPowerPlantController {

    private final IVirtualPowerPlantService service;
    private final ApplicationDomainConverter converter;

    /**
     * Hole alle virtuellen Kraftwerke
     *
     * @return Liste aller VK
     */
    @GetMapping
    public ResponseEntity<?> getAllVirtualPowerPlants() {
        try {
            return new ResponseEntity<>(
                    new ApiResponse(true, false, "Die VK wurden erfolgreich abgefragt.",
                            service.getAll().stream().map(converter::toApplication).collect(Collectors.toList()))
                    , HttpStatus.OK);
        } catch (VirtualPowerPlantServiceException e) {
            log.error(e);
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
     * Hole alle veröffentlichte VK
     *
     * @return Liste veröffentlichter VK
     */
    @GetMapping("/active")
    public ResponseEntity<?> getAllActiveVirtualPowerPlants() {
        try {
            return new ResponseEntity<>(
                    new ApiResponse(true, false, "已成功激活虚拟电厂.",
                            service.getAllActives().stream().map(converter::toApplication).collect(Collectors.toList()))
                    , HttpStatus.OK);
        } catch (VirtualPowerPlantServiceException e) {
            log.error(e);
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
     * Hole spezifisches VK
     *
     * @param virtualPowerPlantId Id des VK
     * @return VK
     */
    @GetMapping(path = "/{virtualPowerPlantId}")
    public ResponseEntity<?> getOneVirtualPowerPlant(@PathVariable String virtualPowerPlantId) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse(true, false, String.format("虚拟电厂 %s 查询成功.", virtualPowerPlantId),
                            converter.toApplication(service.get(virtualPowerPlantId)))
                    , HttpStatus.OK);
        } catch (VirtualPowerPlantServiceException e) {
            log.error(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (DataIntegrityViolationException sqlException) {
            return new ResponseEntity<>(new ApiResponse(
                    false, false, "发生数据完整性错误.", null
            ), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Peristiert ein VK
     *
     * @param dto zu speichernde Daten
     * @return ApiResponse ohne Daten
     */
    @PostMapping
    public ResponseEntity<?> saveVirtualPowerPlant(@RequestBody VirtualPowerPlantDTO dto) {
        try {
            if (dto.isPublished()) {
                throw new VirtualPowerPlantException("虚拟电厂不能频繁创建.");
            }
            service.save(converter.toDomain(dto));
            return ResponseEntity.ok().body(new ApiResponse(true, false, "" +
                    String.format("虚拟电厂%s 已成功创建", dto.getVirtualPowerPlantId()), null));
        } catch (VirtualPowerPlantServiceException | VirtualPowerPlantException e) {
            log.error(e);
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
     * Löscht ein VK
     *
     * @param virtualPowerPlantId Id des VK
     * @return ApiResponse ohne Daten
     */
    @DeleteMapping(path = "/{virtualPowerPlantId}")
    public ResponseEntity<?> deleteVirtualPowerPlant(@PathVariable String virtualPowerPlantId) {
        try {
            service.delete(virtualPowerPlantId);
            return ResponseEntity.ok().body(new ApiResponse(true, false,
                    String.format("虚拟电厂 %s 已成功删除.", virtualPowerPlantId), null));
        } catch (VirtualPowerPlantServiceException e) {
            log.error(e);
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
     * Veröffentlicht ein VK
     *
     * @param virtualPowerPlantId Id des VK
     * @return ApiReponse ohne Daten
     */
    @GetMapping(path = "/{virtualPowerPlantId}/publish")
    public ResponseEntity<?> publishVirtualPowerPlant(@PathVariable String virtualPowerPlantId) {
        try {
            service.publish(virtualPowerPlantId);
            return ResponseEntity.ok().body(new ApiResponse(true, false,
                    String.format("虚拟电厂 %s 已成功发布.", virtualPowerPlantId),
                    null));
        } catch (VirtualPowerPlantServiceException e) {
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
     * Macht die Veröffentlichung rückgängig
     *
     * @param virtualPowerPlantId Id des VK
     * @return ApiResponse ohne Daten
     */
    @GetMapping(path = "/{virtualPowerPlantId}/unpublish")
    public ResponseEntity<?> unpublishVirtualPowerPlant(@PathVariable String virtualPowerPlantId) {
        try {
            service.unpublish(virtualPowerPlantId);
            return ResponseEntity.ok().body(new ApiResponse(true, false,
                    String.format("虚拟电厂 %s 已成功回收.", virtualPowerPlantId),
                    null));
        } catch (VirtualPowerPlantServiceException e) {
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
     * Aktualisiert ein VK
     *
     * @param virtualPowerPlantId Id des VK
     * @param newDto              aktualisierte Daten
     * @return ApiResponse ohne Daten
     */
    @PutMapping(path = "/{virtualPowerPlantId}")
    public ResponseEntity<?> updateVirtualPowerPlant(@PathVariable String virtualPowerPlantId, @RequestBody VirtualPowerPlantDTO newDto) {
        try {
            service.update(virtualPowerPlantId, converter.toDomain(newDto));
            return ResponseEntity.ok().body(new ApiResponse(true, false,
                    String.format("虚拟电厂 %s 已成功更新.", virtualPowerPlantId),
                    null));
        } catch (VirtualPowerPlantServiceException | VirtualPowerPlantException e) {
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
