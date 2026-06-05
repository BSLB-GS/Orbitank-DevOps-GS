package br.com.orbitank.repository;

import br.com.orbitank.entity.MiningRobot;
import br.com.orbitank.enums.RobotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MiningRobotRepository extends JpaRepository<MiningRobot, Long> {
    long countByLunarStationId(Long stationId);
    long countByLunarStationIdAndStatus(Long stationId, RobotStatus status);
    List<MiningRobot> findByLunarStationId(Long stationId);
}