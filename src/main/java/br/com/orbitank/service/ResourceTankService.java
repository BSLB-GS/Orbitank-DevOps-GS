package br.com.orbitank.service;

import br.com.orbitank.dto.Request.ResourceTankRequest;
import br.com.orbitank.dto.Response.ResourceTankResponse;
import br.com.orbitank.entity.LunarStation;
import br.com.orbitank.entity.ResourceTank;
import br.com.orbitank.repository.LunarStationRepository;
import br.com.orbitank.repository.ResourceTankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceTankService {

    private final ResourceTankRepository repository;
    private final LunarStationRepository lunarStationRepository;

    public List<ResourceTankResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ResourceTankResponse findById(Long id) {
        ResourceTank entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tanque não encontrado"));
        return toResponse(entity);
    }

    public ResourceTankResponse create(ResourceTankRequest request) {
        validateCapacity(request.getCurrentVolume(), request.getMaxCapacity());
        return toResponse(repository.save(toEntity(request)));
    }

    public ResourceTankResponse update(Long id, ResourceTankRequest request) {
        validateCapacity(request.getCurrentVolume(), request.getMaxCapacity());

        ResourceTank entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tanque não encontrado"));

        LunarStation station = lunarStationRepository.findById(request.getLunarStationId())
                .orElseThrow(() -> new RuntimeException("Estação Lunar não encontrada com o ID: " + request.getLunarStationId()));

        entity.setLunarStation(station);
        entity.setIdentifier(request.getIdentifier());
        entity.setResourceType(request.getResourceType());
        entity.setMaxCapacity(request.getMaxCapacity());
        entity.setCurrentVolume(request.getCurrentVolume());
        entity.setCurrentPressure(request.getCurrentPressure());
        entity.setCurrentTemperature(request.getCurrentTemperature());
        entity.setStatus(request.getStatus());

        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void validateCapacity(Double currentVolume, Double maxCapacity) {
        if (currentVolume > maxCapacity) {
            throw new RuntimeException("O volume atual (" + currentVolume + ") não pode ser maior que a capacidade máxima (" + maxCapacity + ").");
        }
    }

    private ResourceTankResponse toResponse(ResourceTank entity) {
        return ResourceTankResponse.builder()
                .id(entity.getId())
                .lunarStationId(entity.getLunarStation().getId())
                .identifier(entity.getIdentifier())
                .resourceType(entity.getResourceType())
                .maxCapacity(entity.getMaxCapacity())
                .currentVolume(entity.getCurrentVolume())
                .currentPressure(entity.getCurrentPressure())
                .currentTemperature(entity.getCurrentTemperature())
                .status(entity.getStatus())
                .build();
    }

    private ResourceTank toEntity(ResourceTankRequest request) {
        LunarStation station = lunarStationRepository.findById(request.getLunarStationId())
                .orElseThrow(() -> new RuntimeException("Estação Lunar não encontrada com o ID: " + request.getLunarStationId()));

        return ResourceTank.builder()
                .lunarStation(station)
                .identifier(request.getIdentifier())
                .resourceType(request.getResourceType())
                .maxCapacity(request.getMaxCapacity())
                .currentVolume(request.getCurrentVolume())
                .currentPressure(request.getCurrentPressure())
                .currentTemperature(request.getCurrentTemperature())
                .status(request.getStatus())
                .build();
    }

    public List<ResourceTankResponse> findByStationId(Long stationId) {
        return repository.findByLunarStationId(stationId)
                .stream()
                .map(this::toResponse)
                .toList();
    }
}