package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.LunarStationRequest;
import br.com.orbitank.dto.Response.*;
import br.com.orbitank.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping("/stations")
@RequiredArgsConstructor
public class LunarStationController {

    private final LunarStationService service;
    private final SensorReadingService sensorReadingService;
    private final OperationalAlertService operationalAlertService;
    private final ResourceTankService resourceTankService;
    private final MiningRobotService miningRobotService;

    @GetMapping
    public ResponseEntity<List<LunarStationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LunarStationResponse> findById(@PathVariable Long id) {
        LunarStationResponse response = service.findById(id);

        response.add(linkTo(methodOn(LunarStationController.class).findById(id)).withSelfRel());
        response.add(linkTo(methodOn(LunarStationController.class).getDashboard(id)).withRel("dashboard"));
        response.add(linkTo(methodOn(LunarStationController.class).getStationTanks(id)).withRel("tanks"));
        response.add(linkTo(methodOn(LunarStationController.class).getStationAlerts(id)).withRel("alerts"));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/dashboard")
    public ResponseEntity<StationDashboardResponse> getDashboard(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getDashboard(id));
    }

    @GetMapping("/{id}/sensors/latest-readings")
    public ResponseEntity<List<LatestSensorReadingResponse>> getLatestSensorReadings(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(sensorReadingService.getLatestReadingsByStation(id));
    }

    @GetMapping("/code/{stationCode}")
    public ResponseEntity<LunarStationResponse> findByStationCode(
            @PathVariable Long stationCode
    ) {
        return ResponseEntity.ok(service.findByStationCode(stationCode));
    }

    @PostMapping
    public ResponseEntity<LunarStationResponse> create(
            @RequestBody @Valid LunarStationRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED) // Status 201
                .body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LunarStationResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid LunarStationRequest request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/alerts")
    public ResponseEntity<List<OperationalAlertResponse>> getStationAlerts(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(operationalAlertService.findAlertsByStationId(id));
    }

    @GetMapping("/{id}/alerts/critical")
    public ResponseEntity<List<OperationalAlertResponse>> getStationCriticalAlerts(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(operationalAlertService.findCriticalAlertsByStationId(id));
    }

    @GetMapping("/{id}/tanks")
    public ResponseEntity<List<ResourceTankResponse>> getStationTanks(@PathVariable Long id) {
        return ResponseEntity.ok(resourceTankService.findByStationId(id));
    }

    @GetMapping("/{id}/robots")
    public ResponseEntity<List<MiningRobotResponse>> getStationRobots(@PathVariable Long id) {
        return ResponseEntity.ok(miningRobotService.findByStationId(id));
    }
}