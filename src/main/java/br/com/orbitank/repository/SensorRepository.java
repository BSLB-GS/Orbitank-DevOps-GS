package br.com.orbitank.repository;

import br.com.orbitank.entity.Sensor;
import br.com.orbitank.enums.SensorStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    long countByLunarStationId(Long stationId);
    long countByLunarStationIdAndStatus(Long stationId, SensorStatus status);
}