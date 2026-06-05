package br.com.orbitank.dto.Request;

import br.com.orbitank.enums.SensorStatus;
import br.com.orbitank.enums.SensorType;
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
public class SensorRequest {

    @NotNull(message = "O ID da estação lunar é obrigatório")
    private Long lunarStationId;

    @NotBlank(message = "O identificador do sensor é obrigatório")
    @Size(max = 50, message = "O identificador deve ter no máximo 50 caracteres")
    private String identifier;

    @NotBlank(message = "A localização do sensor é obrigatória")
    @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres")
    private String location;

    @NotNull(message = "O status do sensor é obrigatório")
    private SensorStatus status;

    @NotNull(message = "O tipo do sensor é obrigatório")
    private SensorType sensorType;
}