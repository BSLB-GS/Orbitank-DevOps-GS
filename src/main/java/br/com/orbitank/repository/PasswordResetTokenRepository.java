package br.com.orbitank.repository;

import br.com.orbitank.entity.OperationalUser;
import br.com.orbitank.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByResetToken(String resetToken);

    Optional<PasswordResetToken> findTopByOperationalUserOrderByIdDesc(OperationalUser user);
}