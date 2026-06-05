package br.com.orbitank.dto.Request;

import br.com.orbitank.enums.MissionPriority;
import br.com.orbitank.enums.MissionStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class SpaceMissionRequest {

    @NotBlank(message = "O código da missão é obrigatório")
    @Pattern(
            regexp = "^[A-Z0-9-]+$",
            message = "O código da missão deve conter apenas letras maiúsculas, números e hifens"
    )
    private String missionCode;

    @NotBlank(message = "O nome da missão é obrigatório")
    @Size(max = 120, message = "O nome da missão deve ter no máximo 120 caracteres")
    private String missionName;

    @NotBlank(message = "O nome da empresa é obrigatório")
    @Size(max = 120, message = "O nome da empresa deve ter no máximo 120 caracteres")
    private String clientCompanyName;

    @NotBlank(message = "O código comercial é obrigatório")
    @Size(max = 80, message = "O código comercial externo deve ter no máximo 80 caracteres")
    private String externalCommercialRequestCode;

    @NotBlank(message = "O destino é obrigatório")
    @Size(max = 100, message = "O destino deve ter no máximo 100 caracteres")
    private String destination;

    @NotNull(message = "A data prevista de lançamento é obrigatória")
    @Future(message = "A data de lançamento deve estar no futuro")
    private LocalDateTime scheduledLaunchDate;

    @NotNull(message = "O ID do usuário criador é obrigatório")
    private Long createdByUserId;

    @NotNull(message = "O status da missão é obrigatória")
    private MissionStatus status;

    @NotNull(message = "A prioridade da missão é obrigatória")
    private MissionPriority priority;
}