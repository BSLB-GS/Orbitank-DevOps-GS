package br.com.orbitank.config;

import br.com.orbitank.entity.OperationalUser;
import br.com.orbitank.enums.UserRole;
import br.com.orbitank.enums.UserStatus;
import br.com.orbitank.repository.OperationalUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OperationalUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(OperationalUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@orbitank.com").isEmpty()) {
            OperationalUser admin = OperationalUser.builder()
                    .fullName("Administrador Global")
                    .email("admin@orbitank.com")
                    .passwordHash(passwordEncoder.encode("Orbitank2026!"))
                    .role(UserRole.ADMIN)
                    .status(UserStatus.ACTIVE)
                    .createdAt(LocalDateTime.now())
                    .build();

            userRepository.save(admin);
            System.out.println("🚀 Usuário Admin inicial criado com sucesso!");
        }
    }
}