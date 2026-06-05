package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.ResourceTankRequest;
import br.com.orbitank.dto.Response.ResourceTankResponse;
import br.com.orbitank.service.ResourceTankService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource-tanks")
@RequiredArgsConstructor
public class ResourceTankController {

    private final ResourceTankService service;

    @GetMapping
    public ResponseEntity<List<ResourceTankResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceTankResponse> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ResourceTankResponse> create(
            @RequestBody @Valid ResourceTankRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceTankResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid ResourceTankRequest request
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