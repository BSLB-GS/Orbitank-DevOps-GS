package br.com.orbitank.dto.Response;

import br.com.orbitank.enums.UserRole;
import br.com.orbitank.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationalUserResponse {

    private Long id;

    private String fullName;

    private String email;

    private UserRole role;

    private UserStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime lastLoginAt;
}