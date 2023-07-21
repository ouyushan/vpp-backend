package de.uol.vpp.load.application.rest;

import de.uol.vpp.load.application.ApplicationDomainConverter;
import de.uol.vpp.load.application.payload.ApiResponse;
import de.uol.vpp.load.domain.exceptions.LoadServiceException;
import de.uol.vpp.load.domain.services.ILoadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * REST-Schnittstele für das holen der Lastenwerte einer bestimmten Maßnahmenabfrage
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/load", produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
public class LoadController {

    private final ILoadService loadService;
    private final ApplicationDomainConverter converter;

    @GetMapping(path = "/{actionRequestId}")
    public ResponseEntity<?> getAllLoadsByActionRequestId(@PathVariable String actionRequestId) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse(true, false, "负荷查询成功.",
                            loadService.getLoadsByActionRequestId(actionRequestId)
                                    .stream()
                                    .map(converter::toApplication)
                                    .collect(Collectors.toList())
                    ), HttpStatus.OK);
        } catch (LoadServiceException e) {
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
