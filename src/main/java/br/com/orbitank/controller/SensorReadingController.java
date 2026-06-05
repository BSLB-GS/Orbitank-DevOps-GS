package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.SensorReadingRequest;
import br.com.orbitank.dto.Response.SensorReadingResponse;
import br.com.orbitank.service.SensorReadingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensor-readings")
@RequiredArgsConstructor
public class SensorReadingController {

    private final SensorReadingService service;

    @GetMapping
    public ResponseEntity<List<SensorReadingResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorReadingResponse> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SensorReadingResponse> create(
            @RequestBody @Valid SensorReadingRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorReadingResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid SensorReadingRequest request
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
}