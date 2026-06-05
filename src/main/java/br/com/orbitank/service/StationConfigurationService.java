package br.com.orbitank.service;

import br.com.orbitank.dto.Request.StationConfigurationRequest;
import br.com.orbitank.dto.Response.StationConfigurationResponse;
import br.com.orbitank.entity.LunarStation;
import br.com.orbitank.entity.StationConfiguration;
import br.com.orbitank.repository.LunarStationRepository;
import br.com.orbitank.repository.StationConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StationConfigurationService {

    private final StationConfigurationRepository repository;
    private final LunarStationRepository lunarStationRepository;

    public List<StationConfigurationResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public StationConfigurationResponse findById(Long id) {
        StationConfiguration config = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Configuração não encontrada com o ID: " + id));

        return toResponse(config);
    }

    public StationConfigurationResponse create(StationConfigurationRequest request) {
        StationConfiguration config = toEntity(request);
        return toResponse(repository.save(config));
    }

    public StationConfigurationResponse update(Long id, StationConfigurationRequest request) {
        StationConfiguration config = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Configuração não encontrada com o ID: " + id));

        LunarStation station = null;
        if (request.getLunarStationId() != null) {
            station = lunarStationRepository.findById(request.getLunarStationId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Estação Lunar não encontrada com o ID: " + request.getLunarStationId()));
        }

        config.setLunarStation(station);
        config.setMinimumWaterLevel(request.getMinimumWaterLevel());
        config.setMinimumHydrogenLevel(request.getMinimumHydrogenLevel());
        config.setMinimumOxygenLevel(request.getMinimumOxygenLevel());
        config.setMinimumEnergyLevel(request.getMinimumEnergyLevel());
        config.setMaximumTankPressure(request.getMaximumTankPressure());
        config.setMaximumTemperature(request.getMaximumTemperature());
        config.setMinimumRobotBattery(request.getMinimumRobotBattery());
        config.setUpdatedAt(request.getUpdatedAt());

        StationConfiguration updatedConfig = repository.save(config);

        return toResponse(updatedConfig);
    }

    public void delete(Long id) {
        StationConfiguration config = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Configuração não encontrada com o ID: " + id));
        repository.delete(config);
    }

    private StationConfigurationResponse toResponse(StationConfiguration entity) {
        return StationConfigurationResponse.builder()
                .id(entity.getId())
                .lunarStation(entity.getLunarStation())
                .minimumWaterLevel(entity.getMinimumWaterLevel())
                .minimumHydrogenLevel(entity.getMinimumHydrogenLevel())
                .minimumOxygenLevel(entity.getMinimumOxygenLevel())
                .minimumEnergyLevel(entity.getMinimumEnergyLevel())
                .maximumTankPressure(entity.getMaximumTankPressure())
                .maximumTemperature(entity.getMaximumTemperature())
                .minimumRobotBattery(entity.getMinimumRobotBattery())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private StationConfiguration toEntity(StationConfigurationRequest request) {
        LunarStation station = null;
        if (request.getLunarStationId() != null) {
            station = lunarStationRepository.findById(request.getLunarStationId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Estação Lunar não encontrada com o ID: " + request.getLunarStationId()));
        }

        return StationConfiguration.builder()
                .lunarStation(station)
                .minimumWaterLevel(request.getMinimumWaterLevel())
                .minimumHydrogenLevel(request.getMinimumHydrogenLevel())
                .minimumOxygenLevel(request.getMinimumOxygenLevel())
                .minimumEnergyLevel(request.getMinimumEnergyLevel())
                .maximumTankPressure(request.getMaximumTankPressure())
                .maximumTemperature(request.getMaximumTemperature())
                .minimumRobotBattery(request.getMinimumRobotBattery())
                .updatedAt(request.getUpdatedAt())
                .build();
    }
}