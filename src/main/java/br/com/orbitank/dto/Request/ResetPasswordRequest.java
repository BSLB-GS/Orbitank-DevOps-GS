package br.com.orbitank.dto.Request;

public record ResetPasswordRequest(
        String resetToken,
        String newPassword,
        String confirmPassword
) {}