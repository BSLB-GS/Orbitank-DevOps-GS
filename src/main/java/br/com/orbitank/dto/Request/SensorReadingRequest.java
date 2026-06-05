package br.com.orbitank.dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorReadingRequest {

    @NotNull(message = "O ID do sensor é obrigatório")
    private Long sensorId;

    @NotNull(message = "O valor da leitura é obrigatório")
    private Double readingValue;

    @NotNull
    @PastOrPresent(message = "A data/hora da leitura não pode estar no futuro")
    private LocalDateTime timestamp;
}