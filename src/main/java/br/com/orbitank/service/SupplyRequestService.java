package br.com.orbitank.service;

import br.com.orbitank.dto.Request.SupplyRequestRequest;
import br.com.orbitank.dto.Response.SupplyRequestResponse;
import br.com.orbitank.entity.*;
import br.com.orbitank.enums.*;
import br.com.orbitank.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplyRequestService {

    private final SupplyRequestRepository repository;
    private final SpaceMissionRepository spaceMissionRepository;
    private final LunarStationRepository lunarStationRepository;
    private final ResourceTankRepository tankRepository;
    private final OperationalAlertRepository alertRepository;

    public List<SupplyRequestResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public SupplyRequestResponse findById(Long id) {
        SupplyRequest request = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada com o ID: " + id));
        return toResponse(request);
    }

    public SupplyRequestResponse create(SupplyRequestRequest requestDto) {
        validateRequestedVolumes(requestDto);
        SupplyRequest request = toEntity(requestDto);
        return toResponse(repository.save(request));
    }

    public SupplyRequestResponse update(Long id, SupplyRequestRequest requestDto) {
        validateRequestedVolumes(requestDto);
        SupplyRequest request = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada com o ID: " + id));

        SpaceMission mission = spaceMissionRepository.findById(requestDto.getMissionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Missão não encontrada com o ID: " + requestDto.getMissionId()));

        request.setMission(mission);
        request.setRequestedWaterVolume(requestDto.getRequestedWaterVolume());
        request.setRequestedH2Volume(requestDto.getRequestedH2Volume());
        request.setRequestedO2Volume(requestDto.getRequestedO2Volume());
        request.setRequestDate(requestDto.getRequestDate());
        request.setDenialReason(requestDto.getDenialReason());
        request.setStatus(requestDto.getStatus());

        return toResponse(repository.save(request));
    }

    public void delete(Long id) {
        SupplyRequest request = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada com o ID: " + id));
        repository.delete(request);
    }

    public SupplyRequestResponse analyzeRequest(Long id, Long stationId) {
        SupplyRequest request = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada para análise."));

        LunarStation station = lunarStationRepository.findById(stationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estação não encontrada."));

        boolean isStationOffline = station.getStatus() != StationStatus.ONLINE;
        boolean hasCriticalAlerts = alertRepository.countByLunarStationIdAndSeverityAndActiveTrue(stationId, AlertSeverity.CRITICAL) > 0;
        boolean hasUnsafeTanks = tankRepository.existsByLunarStationIdAndStatusIn(stationId, List.of(TankStatus.CRITICAL, TankStatus.OFFLINE));

        if (isStationOffline || hasCriticalAlerts || hasUnsafeTanks) {
            request.setStatus(SupplyRequestStatus.DENIED);
            request.setDenialReason("Bloqueio Operacional: Estação offline, alertas críticos ativos ou tanques em risco.");
            return toResponse(repository.save(request));
        }

        Double availableWater = tankRepository.sumVolumeByStationAndType(stationId, ResourceType.LIQUID_WATER);
        Double availableH2 = tankRepository.sumVolumeByStationAndType(stationId, ResourceType.HYDROGEN);
        Double availableO2 = tankRepository.sumVolumeByStationAndType(stationId, ResourceType.OXYGEN);

        boolean hasEnoughWater = availableWater >= request.getRequestedWaterVolume();
        boolean hasEnoughH2 = availableH2 >= request.getRequestedH2Volume();
        boolean hasEnoughO2 = availableO2 >= request.getRequestedO2Volume();

        if (!hasEnoughWater || !hasEnoughH2 || !hasEnoughO2) {
            request.setStatus(SupplyRequestStatus.WAITING_FOR_RESOURCES);
            request.setDenialReason("Recursos insuficientes nos tanques para atender à solicitação.");
            return toResponse(repository.save(request));
        }

        request.setStatus(SupplyRequestStatus.APPROVED);
        request.setDenialReason(null);
        return toResponse(repository.save(request));
    }

    private void validateRequestedVolumes(SupplyRequestRequest request) {
        if (request.getRequestedWaterVolume() <= 0 &&
                request.getRequestedH2Volume() <= 0 &&
                request.getRequestedO2Volume() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pelo menos um dos volumes solicitados deve ser maior que zero.");
        }
    }

    private SupplyRequestResponse toResponse(SupplyRequest entity) {
        return SupplyRequestResponse.builder()
                .id(entity.getId())
                .missionId(entity.getMission().getId())
                .requestedWaterVolume(entity.getRequestedWaterVolume())
                .requestedH2Volume(entity.getRequestedH2Volume())
                .requestedO2Volume(entity.getRequestedO2Volume())
                .requestDate(entity.getRequestDate())
                .denialReason(entity.getDenialReason())
                .status(entity.getStatus())
                .build();
    }

    private SupplyRequest toEntity(SupplyRequestRequest requestDto) {
        SpaceMission mission = spaceMissionRepository.findById(requestDto.getMissionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Missão não encontrada com o ID: " + requestDto.getMissionId()));

        return SupplyRequest.builder()
                .mission(mission)
                .requestedWaterVolume(requestDto.getRequestedWaterVolume())
                .requestedH2Volume(requestDto.getRequestedH2Volume())
                .requestedO2Volume(requestDto.getRequestedO2Volume())
                .requestDate(requestDto.getRequestDate())
                .denialReason(requestDto.getDenialReason())
                .status(requestDto.getStatus())
                .build();
    }
}