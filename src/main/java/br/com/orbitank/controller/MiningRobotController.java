package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.MiningRobotRequest;
import br.com.orbitank.dto.Response.MiningRobotResponse;
import br.com.orbitank.service.MiningRobotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mining-robots")
@RequiredArgsConstructor
public class MiningRobotController {

    private final MiningRobotService service;

    @GetMapping
    public ResponseEntity<List<MiningRobotResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MiningRobotResponse> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<MiningRobotResponse> create(
            @RequestBody @Valid MiningRobotRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED) // Status 201
                .body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MiningRobotResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid MiningRobotRequest request
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