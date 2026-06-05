package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.OperationalAlertRequest;
import br.com.orbitank.dto.Response.OperationalAlertResponse;
import br.com.orbitank.service.OperationalAlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operational-alerts")
@RequiredArgsConstructor
public class OperationalAlertController {

    private final OperationalAlertService service;

    @GetMapping
    public ResponseEntity<List<OperationalAlertResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationalAlertResponse> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OperationalAlertResponse> create(
            @RequestBody @Valid OperationalAlertRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED) // Status 201
                .body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationalAlertResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid OperationalAlertRequest request
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