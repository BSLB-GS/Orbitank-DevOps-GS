package br.com.orbitank.dto.Response;

import br.com.orbitank.enums.SensorStatus;
import br.com.orbitank.enums.SensorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LatestSensorReadingResponse {
    private Long sensorId;
    private String identifier;
    private SensorType type;
    private String location;
    private Double readingValue;
    private SensorStatus status;
    private LocalDateTime lastReadingAt;
}