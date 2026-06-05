package br.com.orbitank.repository;

import br.com.orbitank.entity.SpaceMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpaceMissionRepository
        extends JpaRepository<SpaceMission, Long> {
    List<SpaceMission> findByStatus(br.com.orbitank.enums.MissionStatus status);
}