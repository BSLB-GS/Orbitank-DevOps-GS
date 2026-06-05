package br.com.orbitank.dto.Response;

import lombok.Builder;

@Builder
public record StationDashboardResponse(
        String stationName,
        String stationStatus,
        Double iceAvailable,
        Double waterAvailable,
        Double hydrogenAvailable,
        Double oxygenAvailable,
        Long activeRobots,
        Long totalRobots,
        Long activeSensors,
        Long totalSensors,
        Long criticalAlerts,
        Long missionsAwaitingFuel
) {}