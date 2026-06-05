package br.com.orbitank.dto.Request;

public record VerifyResetCodeRequest(
        String email,
        String code
) {}