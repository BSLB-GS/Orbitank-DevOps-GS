package br.com.orbitank.service;

import br.com.orbitank.dto.Request.LoginRequest;
import br.com.orbitank.dto.Response.LoginResponse;
import br.com.orbitank.entity.OperationalUser;
import br.com.orbitank.entity.PasswordResetToken;
import br.com.orbitank.repository.OperationalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OperationalUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    private final PasswordResetService passwordResetService;

    public LoginResponse login(LoginRequest request) {
        OperationalUser user = repository
                .findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .userId(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .status(user.getStatus().name())
                .token(token)
                .build();
    }

    public void forgotPassword(String email) {
        repository.findByEmail(email).ifPresent(user -> {

            String code = String.format("%06d", new Random().nextInt(999999));

            passwordResetService.createResetCode(user, code);

            emailService.sendVerificationCode(user.getEmail(), code);
        });
    }

    public String verifyResetCode(String email, String code) {
        OperationalUser user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return passwordResetService.verifyCodeAndGenerateToken(user, code);
    }

    public void resetPassword(String resetToken, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("As senhas não coincidem.");
        }

        PasswordResetToken validToken = passwordResetService.validateAndConsumeToken(resetToken);

        OperationalUser user = validToken.getOperationalUser();

        user.setPasswordHash(passwordEncoder.encode(newPassword));
        repository.save(user);
    }
}