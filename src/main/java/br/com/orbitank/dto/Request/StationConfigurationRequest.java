package br.com.orbitank.dto.Request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationConfigurationRequest {

    private Long lunarStationId;

    @NotNull
    private Double minimumWaterLevel;

    @NotNull
    private Double minimumHydrogenLevel;

    @NotNull
    private Double minimumOxygenLevel;

    @NotNull
    private Double minimumEnergyLevel;

    @NotNull
    private Double maximumTankPressure;

    @NotNull
    private Double maximumTemperature;

    @NotNull
    private Double minimumRobotBattery;

    @NotNull
    @PastOrPresent
    private LocalDateTime updatedAt;
}