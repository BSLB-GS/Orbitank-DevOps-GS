package br.com.orbitank.service;

import br.com.orbitank.dto.Request.OperationalAlertRequest;
import br.com.orbitank.dto.Response.OperationalAlertResponse;
import br.com.orbitank.entity.LunarStation;
import br.com.orbitank.entity.OperationalAlert;
import br.com.orbitank.repository.LunarStationRepository;
import br.com.orbitank.repository.OperationalAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationalAlertService {

    private final OperationalAlertRepository repository;
    private final LunarStationRepository lunarStationRepository;

    public List<OperationalAlertResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public OperationalAlertResponse findById(Long id) {
        OperationalAlert entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));

        return toResponse(entity);
    }

    public OperationalAlertResponse create(OperationalAlertRequest request) {
        return toResponse(repository.save(toEntity(request)));
    }

    public OperationalAlertResponse update(Long id, OperationalAlertRequest request) {
        OperationalAlert entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));

        LunarStation station = lunarStationRepository.findById(request.getLunarStationId())
                .orElseThrow(() -> new RuntimeException("Estação Lunar não encontrada com o ID: " + request.getLunarStationId()));

        entity.setLunarStation(station);
        entity.setSource(request.getSource());
        entity.setMessage(request.getMessage());
        entity.setActive(request.getActive());
        entity.setTriggeredAt(request.getTriggeredAt());
        entity.setResolvedAt(request.getResolvedAt());
        entity.setSeverity(request.getSeverity());

        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private OperationalAlertResponse toResponse(OperationalAlert entity) {
        return OperationalAlertResponse.builder()
                .id(entity.getId())
                .lunarStationId(entity.getLunarStation().getId())
                .source(entity.getSource())
                .message(entity.getMessage())
                .active(entity.getActive())
                .triggeredAt(entity.getTriggeredAt())
                .resolvedAt(entity.getResolvedAt())
                .severity(entity.getSeverity())
                .build();
    }

    private OperationalAlert toEntity(OperationalAlertRequest request) {
        LunarStation station = lunarStationRepository.findById(request.getLunarStationId())
                .orElseThrow(() -> new RuntimeException("Estação Lunar não encontrada com o ID: " + request.getLunarStationId()));

        return OperationalAlert.builder()
                .lunarStation(station)
                .source(request.getSource())
                .message(request.getMessage())
                .active(request.getActive())
                .triggeredAt(request.getTriggeredAt())
                .resolvedAt(request.getResolvedAt())
                .severity(request.getSeverity())
                .build();
    }

    public List<OperationalAlertResponse> findAlertsByStationId(Long stationId) {
        return repository.findByLunarStationId(stationId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<OperationalAlertResponse> findCriticalAlertsByStationId(Long stationId) {

        return repository.findByLunarStationIdAndSeverity(stationId, br.com.orbitank.enums.AlertSeverity.CRITICAL)
                .stream()
                .map(this::toResponse)
                .toList();
    }
}
