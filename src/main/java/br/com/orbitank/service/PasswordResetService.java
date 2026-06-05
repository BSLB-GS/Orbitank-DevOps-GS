package br.com.orbitank.service;

import br.com.orbitank.entity.OperationalUser;
import br.com.orbitank.entity.PasswordResetToken;
import br.com.orbitank.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;

    public void createResetCode(OperationalUser user, String code) {
        PasswordResetToken token = PasswordResetToken.builder()
                .operationalUser(user)
                .codeHash(code)
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .build();

        tokenRepository.save(token);
    }

    public String verifyCodeAndGenerateToken(OperationalUser user, String code) {
        PasswordResetToken token = tokenRepository.findTopByOperationalUserOrderByIdDesc(user)
                .orElseThrow(() -> new RuntimeException("Nenhuma solicitação de recuperação encontrada."));

        if (token.getUsedAt() != null || token.getVerifiedAt() != null) {
            throw new RuntimeException("Este código já foi utilizado.");
        }
        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("O código expirou.");
        }
        if (!token.getCodeHash().equals(code)) {
            throw new RuntimeException("Código de verificação inválido.");
        }

        token.setVerifiedAt(LocalDateTime.now());
        String resetToken = UUID.randomUUID().toString();
        token.setResetToken(resetToken);

        tokenRepository.save(token);
        return resetToken;
    }

    public PasswordResetToken validateAndConsumeToken(String resetTokenString) {
        PasswordResetToken token = tokenRepository.findByResetToken(resetTokenString)
                .orElseThrow(() -> new RuntimeException("Token inválido ou não encontrado."));

        if (token.getUsedAt() != null) {
            throw new RuntimeException("Este link/token já foi utilizado para redefinir a senha.");
        }
        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("O tempo limite para redefinir a senha expirou.");
        }

        token.setUsedAt(LocalDateTime.now());
        return tokenRepository.save(token);
    }
}