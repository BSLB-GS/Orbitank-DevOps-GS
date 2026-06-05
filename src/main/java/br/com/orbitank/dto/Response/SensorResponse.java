package br.com.orbitank.dto.Response;

import br.com.orbitank.enums.SensorStatus;
import br.com.orbitank.enums.SensorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorResponse {
    private Long id;
    private Long lunarStationId;
    private String identifier;
    private String location;
    private SensorStatus status;
    private SensorType sensorType;
}