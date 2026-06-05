package br.com.orbitank.dto.Response;

import lombok.Builder;

@Builder
public record TelemetryRealtimeResponse(
        Long stationCode,
        Double iceLevelPercent,
        Double waterLevelPercent,
        Double hydrogenLevelPercent,
        Double oxygenLevelPercent,
        Double energyLevelPercent,
        Double temperatureCelsius,
        Double humidityPercent,
        String moduleStatus,
        String riskLevel,
        Boolean alertActive,
        String alertType,
        String alertMessage,
        String alertSeverity
) {}