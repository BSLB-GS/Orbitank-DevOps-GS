package br.com.orbitank.dto.Request;

public record LoginRequest(
        String email,
        String password
) {}