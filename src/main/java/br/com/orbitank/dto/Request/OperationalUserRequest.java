package br.com.orbitank.dto.Request;

import br.com.orbitank.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationalUserRequest {

    @NotBlank(message = "O nome do usuário é obrigatório")
    @Size(min = 3, max = 100)
    private String fullName;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String password;

    @NotNull
    private UserRole role;
}