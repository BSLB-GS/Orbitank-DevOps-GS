package br.com.orbitank.dto.Request;

import br.com.orbitank.enums.ResourceType;
import br.com.orbitank.enums.TankStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceTankRequest {

    @NotNull(message = "O ID da estação lunar é obrigatório")
    private Long lunarStationId;

    @NotBlank(message = "O identificador é obrigatório")
    @Size(min = 3, max = 50)
    private String identifier;

    @NotNull(message = "O tipo de recurso é obrigatório")
    private ResourceType resourceType;

    @NotNull(message = "A capacidade máxima é obrigatória")
    @Positive
    private Double maxCapacity;

    @NotNull(message = "O volume atual é obrigatório")
    @PositiveOrZero
    private Double currentVolume;

    @NotNull(message = "A pressão atual é obrigatória")
    @PositiveOrZero
    private Double currentPressure;

    @NotNull(message = "A temperatura atual é obrigatória")
    private Double currentTemperature;

    @NotNull(message = "O status do tanque é obrigatório")
    private TankStatus status;
}