package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.SupplyRequestRequest;
import br.com.orbitank.dto.Response.SupplyRequestResponse;
import br.com.orbitank.service.SupplyRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supply-requests")
@RequiredArgsConstructor
public class SupplyRequestController {

    private final SupplyRequestService service;

    @GetMapping
    public ResponseEntity<List<SupplyRequestResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplyRequestResponse> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SupplyRequestResponse> create(
            @RequestBody @Valid SupplyRequestRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplyRequestResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid SupplyRequestRequest request
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

    @PostMapping("/{id}/analyze")
    public ResponseEntity<SupplyRequestResponse> analyzeRequest(
            @PathVariable Long id,
            @RequestParam Long stationId
    ) {
        return ResponseEntity.ok(service.analyzeRequest(id, stationId));
    }
}