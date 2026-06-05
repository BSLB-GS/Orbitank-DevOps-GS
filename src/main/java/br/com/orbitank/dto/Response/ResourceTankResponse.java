package br.com.orbitank.dto.Response;

import br.com.orbitank.enums.ResourceType;
import br.com.orbitank.enums.TankStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceTankResponse {
    private Long id;
    private Long lunarStationId;
    private String identifier;
    private ResourceType resourceType;
    private Double maxCapacity;
    private Double currentVolume;
    private Double currentPressure;
    private Double currentTemperature;
    private TankStatus status;
}