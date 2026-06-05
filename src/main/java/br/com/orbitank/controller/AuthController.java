package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.ForgotPasswordRequest;
import br.com.orbitank.dto.Request.LoginRequest;
import br.com.orbitank.dto.Request.ResetPasswordRequest;
import br.com.orbitank.dto.Request.VerifyCodeRequest;
import br.com.orbitank.dto.Response.LoginResponse;
import br.com.orbitank.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<java.util.Map<String, String>> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        service.forgotPassword(request.email());
        return ResponseEntity.ok(
                java.util.Map.of("message", "Se o e-mail estiver cadastrado, um código de verificação será enviado.")
        );
    }

    @PostMapping("/verify-reset-code")
    public ResponseEntity<java.util.Map<String, String>> verifyResetCode(@RequestBody VerifyCodeRequest request) {
        String token = service.verifyResetCode(request.getEmail(), request.getCode());
        return ResponseEntity.ok(
                java.util.Map.of("resetToken", token)
        );
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<java.util.Map<String, String>> resetPassword(@RequestBody ResetPasswordRequest request) {

        service.resetPassword(request.resetToken(), request.newPassword(), request.confirmPassword());

        return ResponseEntity.ok(
                java.util.Map.of("message", "Senha redefinida com sucesso.")
        );
    }
}