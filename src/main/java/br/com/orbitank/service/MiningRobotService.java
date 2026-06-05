package br.com.orbitank.service;

import br.com.orbitank.dto.Request.MiningRobotRequest;
import br.com.orbitank.dto.Response.MiningRobotResponse;
import br.com.orbitank.entity.LunarStation;
import br.com.orbitank.entity.MiningRobot;
import br.com.orbitank.repository.LunarStationRepository;
import br.com.orbitank.repository.MiningRobotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MiningRobotService {

    private final MiningRobotRepository repository;
    private final LunarStationRepository lunarStationRepository;

    public List<MiningRobotResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public MiningRobotResponse findById(Long id) {
        MiningRobot entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Robô não encontrado"));

        return toResponse(entity);
    }

    public MiningRobotResponse create(MiningRobotRequest request) {
        return toResponse(repository.save(toEntity(request)));
    }

    public MiningRobotResponse update(Long id, MiningRobotRequest request) {
        MiningRobot entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Robô não encontrado"));

        LunarStation station = lunarStationRepository.findById(request.getLunarStationId())
                .orElseThrow(() -> new RuntimeException("Estação Lunar não encontrada com o ID: " + request.getLunarStationId()));

        entity.setLunarStation(station);
        entity.setIdentification(request.getIdentification());
        entity.setCurrentVolume(request.getCurrentVolume());
        entity.setCargoCapacity(request.getCargoCapacity());
        entity.setCurrentIceCargo(request.getCurrentIceCargo());
        entity.setBatteryLevel(request.getBatteryLevel());
        entity.setStatus(request.getStatus());

        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private MiningRobotResponse toResponse(MiningRobot entity) {
        return MiningRobotResponse.builder()
                .id(entity.getId())
                .lunarStationId(entity.getLunarStation().getId())
                .identification(entity.getIdentification())
                .currentVolume(entity.getCurrentVolume())
                .cargoCapacity(entity.getCargoCapacity())
                .currentIceCargo(entity.getCurrentIceCargo())
                .batteryLevel(entity.getBatteryLevel())
                .status(entity.getStatus())
                .build();
    }

    private MiningRobot toEntity(MiningRobotRequest request) {
        LunarStation station = lunarStationRepository.findById(request.getLunarStationId())
                .orElseThrow(() -> new RuntimeException("Estação Lunar não encontrada com o ID: " + request.getLunarStationId()));

        return MiningRobot.builder()
                .lunarStation(station)
                .identification(request.getIdentification())
                .currentVolume(request.getCurrentVolume())
                .cargoCapacity(request.getCargoCapacity())
                .currentIceCargo(request.getCurrentIceCargo())
                .batteryLevel(request.getBatteryLevel())
                .status(request.getStatus())
                .build();
    }

    public List<MiningRobotResponse> findByStationId(Long stationId) {
        return repository.findByLunarStationId(stationId)
                .stream()
                .map(this::toResponse)
                .toList();
    }
}