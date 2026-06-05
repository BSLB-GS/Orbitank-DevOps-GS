package br.com.orbitank.dto.Response;

import br.com.orbitank.enums.RobotStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MiningRobotResponse {
    private Long id;
    private Long lunarStationId;
    private String identification;
    private Double currentVolume;
    private Double cargoCapacity;
    private Double currentIceCargo;
    private Double batteryLevel;
    private RobotStatus status;
}