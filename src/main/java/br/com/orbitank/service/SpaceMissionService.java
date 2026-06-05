package br.com.orbitank.service;

import br.com.orbitank.dto.Request.SpaceMissionRequest;
import br.com.orbitank.dto.Response.SpaceMissionResponse;
import br.com.orbitank.entity.OperationalUser;
import br.com.orbitank.entity.SpaceMission;
import br.com.orbitank.repository.OperationalUserRepository;
import br.com.orbitank.repository.SpaceMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceMissionService {

    private final SpaceMissionRepository repository;
    private final OperationalUserRepository userRepository;

    public List<SpaceMissionResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public SpaceMissionResponse findById(Long id) {
        SpaceMission entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão espacial não encontrada"));

        return toResponse(entity);
    }

    public SpaceMissionResponse create(SpaceMissionRequest request) {
        SpaceMission entity = toEntity(request);
        return toResponse(repository.save(entity));
    }

    public SpaceMissionResponse update(Long id, SpaceMissionRequest request) {
        SpaceMission entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão espacial não encontrada"));

        OperationalUser createdBy = userRepository.findById(request.getCreatedByUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        entity.setMissionCode(request.getMissionCode());
        entity.setMissionName(request.getMissionName());
        entity.setClientCompanyName(request.getClientCompanyName());
        entity.setExternalCommercialRequestCode(request.getExternalCommercialRequestCode());
        entity.setDestination(request.getDestination());
        entity.setScheduledLaunchDate(request.getScheduledLaunchDate());
        entity.setCreatedBy(createdBy);
        entity.setStatus(request.getStatus());
        entity.setPriority(request.getPriority());

        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private SpaceMissionResponse toResponse(SpaceMission entity) {
        return SpaceMissionResponse.builder()
                .id(entity.getId())
                .missionCode(entity.getMissionCode())
                .missionName(entity.getMissionName())
                .clientCompanyName(entity.getClientCompanyName())
                .externalCommercialRequestCode(entity.getExternalCommercialRequestCode())
                .destination(entity.getDestination())
                .scheduledLaunchDate(entity.getScheduledLaunchDate())
                .createdBy(entity.getCreatedBy())
                .status(entity.getStatus())
                .priority(entity.getPriority())
                .build();
    }

    private SpaceMission toEntity(SpaceMissionRequest request) {
        OperationalUser createdBy = userRepository.findById(request.getCreatedByUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return SpaceMission.builder()
                .missionCode(request.getMissionCode())
                .missionName(request.getMissionName())
                .clientCompanyName(request.getClientCompanyName())
                .externalCommercialRequestCode(request.getExternalCommercialRequestCode())
                .destination(request.getDestination())
                .scheduledLaunchDate(request.getScheduledLaunchDate())
                .createdBy(createdBy)
                .status(request.getStatus())
                .priority(request.getPriority())
                .build();
    }

    public List<SpaceMissionResponse> findAwaitingFuel() {
        return repository.findByStatus(br.com.orbitank.enums.MissionStatus.AWAITING_FUEL)
                .stream()
                .map(this::toResponse)
                .toList();
    }

}