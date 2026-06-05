package br.com.orbitank.repository;

import br.com.orbitank.entity.OperationalAlert;
import br.com.orbitank.enums.AlertSeverity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationalAlertRepository extends JpaRepository<OperationalAlert, Long> {

    long countByLunarStationIdAndSeverityAndActiveTrue(Long stationId, AlertSeverity severity);

    List<OperationalAlert> findByLunarStationId(Long stationId);

    List<OperationalAlert> findByLunarStationIdAndSeverity(Long stationId, AlertSeverity severity);
}