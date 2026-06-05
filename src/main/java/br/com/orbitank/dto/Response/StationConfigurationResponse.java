package br.com.orbitank.dto.Response;

import br.com.orbitank.entity.LunarStation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationConfigurationResponse {
    private Long id;
    private LunarStation lunarStation;
    private Double minimumWaterLevel;
    private Double minimumHydrogenLevel;
    private Double minimumOxygenLevel;
    private Double minimumEnergyLevel;
    private Double maximumTankPressure;
    private Double maximumTemperature;
    private Double minimumRobotBattery;
    private LocalDateTime updatedAt;
}