package br.com.orbitank.dto.Request;

import br.com.orbitank.enums.AlertSeverity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationalAlertRequest {

    @NotNull(message = "O ID da estação lunar é obrigatório")
    private Long lunarStationId;

    @NotBlank(message = "A origem do alerta deve ser identificada")
    private String source;

    @NotBlank(message = "A mensagem do alerta é obrigatória")
    @Size(max = 500)
    private String message;

    @NotNull(message = "O status de atividade do alerta é obrigatório")
    private Boolean active;

    @NotNull(message = "A data de disparo é obrigatória")
    @PastOrPresent
    private LocalDateTime triggeredAt;

    private LocalDateTime resolvedAt;

    @NotNull(message = "A gravidade do alerta é obrigatória")
    private AlertSeverity severity;
}