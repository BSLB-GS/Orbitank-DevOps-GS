package br.com.orbitank.dto.Request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogRequest {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long userId;

    @NotBlank
    @Size(max = 80)
    private String action;

    @NotBlank
    @Size(max = 500)
    private String description;

    @NotNull
    @PastOrPresent
    private LocalDateTime createdAt;
}