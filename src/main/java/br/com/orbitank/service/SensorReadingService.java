package br.com.orbitank.service;

import br.com.orbitank.dto.Request.SensorReadingRequest;
import br.com.orbitank.dto.Response.SensorReadingResponse;
import br.com.orbitank.entity.Sensor;
import br.com.orbitank.entity.SensorReading;
import br.com.orbitank.repository.SensorReadingRepository;
import br.com.orbitank.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorReadingService {

    private final SensorReadingRepository repository;
    private final SensorRepository sensorRepository;

    public List<SensorReadingResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public SensorReadingResponse findById(Long id) {

        SensorReading entity = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Leitura do sensor não encontrada"));

        return toResponse(entity);
    }

    public SensorReadingResponse create(SensorReadingRequest request) {

        SensorReading entity = toEntity(request);

        return toResponse(repository.save(entity));
    }

    public SensorReadingResponse update(Long id, SensorReadingRequest request) {

        SensorReading entity = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Leitura do sensor não encontrada"));

        Sensor sensor = sensorRepository.findById(
                request.getSensorId()
        ).orElseThrow(() ->
                new RuntimeException("Sensor não encontrado"));

        entity.setSensor(sensor);
        entity.setReadingValue(request.getReadingValue());
        entity.setTimestamp(request.getTimestamp());

        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private SensorReadingResponse toResponse(SensorReading entity) {

        return SensorReadingResponse.builder()
                .id(entity.getId())
                .sensorId(entity.getSensor().getId())
                .readingValue(entity.getReadingValue())
                .timestamp(entity.getTimestamp())
                .build();
    }

    private SensorReading toEntity(SensorReadingRequest request) {

        Sensor sensor = sensorRepository.findById(
                request.getSensorId()
        ).orElseThrow(() ->
                new RuntimeException("Sensor não encontrado"));

        return SensorReading.builder()
                .sensor(sensor)
                .readingValue(request.getReadingValue())
                .timestamp(request.getTimestamp())
                .build();
    }

    public List<br.com.orbitank.dto.Response.LatestSensorReadingResponse> getLatestReadingsByStation(Long stationId) {
        List<SensorReading> latestReadings = repository.findLatestReadingsByStationId(stationId);

        return latestReadings.stream().map(reading ->
                br.com.orbitank.dto.Response.LatestSensorReadingResponse.builder()
                        .sensorId(reading.getSensor().getId())
                        .identifier(reading.getSensor().getIdentifier())
                        .type(reading.getSensor().getSensorType())
                        .location(reading.getSensor().getLocation())
                        .readingValue(reading.getReadingValue())
                        .status(reading.getSensor().getStatus())
                        .lastReadingAt(reading.getTimestamp())
                        .build()
        ).toList();
    }
}

