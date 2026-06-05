package br.com.orbitank.repository;

import br.com.orbitank.entity.OperationalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationalUserRepository
        extends JpaRepository<OperationalUser, Long> {

    Optional<OperationalUser> findByEmail(String email);

    boolean existsByEmail(String email);
}