package br.com.orbitank.repository;

import br.com.orbitank.entity.LunarStation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LunarStationRepository extends JpaRepository<LunarStation, Long> {
    Optional<LunarStation> findByStationCode(Long stationCode);
}