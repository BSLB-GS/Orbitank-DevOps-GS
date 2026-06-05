package br.com.orbitank.repository;

import br.com.orbitank.entity.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {

    @Query("SELECT sr FROM SensorReading sr WHERE sr.sensor.lunarStation.id = :stationId " +
            "AND sr.timestamp = (SELECT MAX(sr2.timestamp) FROM SensorReading sr2 WHERE sr2.sensor.id = sr.sensor.id)")
    List<SensorReading> findLatestReadingsByStationId(@Param("stationId") Long stationId);
}