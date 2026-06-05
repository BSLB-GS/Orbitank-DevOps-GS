package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.SpaceMissionRequest;
import br.com.orbitank.dto.Response.SpaceMissionResponse;
import br.com.orbitank.service.SpaceMissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class SpaceMissionController {

    private final SpaceMissionService service;

    @GetMapping
    public ResponseEntity<List<SpaceMissionResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceMissionResponse> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SpaceMissionResponse> create(
            @RequestBody @Valid SpaceMissionRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceMissionResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid SpaceMissionRequest request
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

    @GetMapping("/awaiting-fuel")
    public ResponseEntity<List<SpaceMissionResponse>> getMissionsAwaitingFuel() {
        return ResponseEntity.ok(service.findAwaitingFuel());
    }
}