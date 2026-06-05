package br.com.orbitank.dto.Request;

import br.com.orbitank.enums.RobotStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MiningRobotRequest {

    @NotNull(message = "O ID da estação lunar é obrigatório")
    private Long lunarStationId;

    @NotBlank(message = "A identificação é obrigatória")
    @Size(min = 3, max = 50)
    private String identification;

    @NotNull
    @PositiveOrZero
    private Double currentVolume;

    @NotNull
    @Positive
    private Double cargoCapacity;

    @NotNull
    @PositiveOrZero
    private Double currentIceCargo;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    private Double batteryLevel;

    @NotNull(message = "O status do robô é obrigatório")
    private RobotStatus status;
}