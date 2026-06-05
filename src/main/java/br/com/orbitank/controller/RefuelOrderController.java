package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.RefuelOrderRequest;
import br.com.orbitank.dto.Response.RefuelOrderResponse;
import br.com.orbitank.service.RefuelOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refuel-orders")
@RequiredArgsConstructor
public class RefuelOrderController {

    private final RefuelOrderService service;

    @GetMapping
    public ResponseEntity<List<RefuelOrderResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefuelOrderResponse> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<RefuelOrderResponse> create(@RequestBody @Valid RefuelOrderRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @PostMapping("/from-supply-request/{id}")
    public ResponseEntity<RefuelOrderResponse> createFromSupplyRequest(
            @PathVariable Long id,
            @RequestParam Long stationId,
            @RequestBody @Valid RefuelOrderRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createFromSupplyRequest(id, stationId, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RefuelOrderResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid RefuelOrderRequest request
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