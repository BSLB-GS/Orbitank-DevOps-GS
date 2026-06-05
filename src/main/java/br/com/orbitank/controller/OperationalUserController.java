package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.OperationalUserRequest;
import br.com.orbitank.dto.Response.OperationalUserResponse;
import br.com.orbitank.service.OperationalUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/operational-users")
@RequiredArgsConstructor
public class OperationalUserController {

    private final OperationalUserService service;

    @GetMapping
    public ResponseEntity<List<OperationalUserResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationalUserResponse> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OperationalUserResponse> create(
            @RequestBody @Valid OperationalUserRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationalUserResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid OperationalUserRequest request
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