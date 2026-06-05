package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.IotTelemetryRequest;
import br.com.orbitank.service.IotTelemetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus; // Import necessário
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iot")
@RequiredArgsConstructor
public class IotTelemetryController {

    private final IotTelemetryService service;

    @PostMapping("/telemetry")
    public ResponseEntity<Void> receiveTelemetry(@RequestBody IotTelemetryRequest request) {
        service.processTelemetry(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}