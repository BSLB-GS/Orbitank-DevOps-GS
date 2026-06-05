package br.com.orbitank.repository;

import br.com.orbitank.entity.ResourceTank;
import br.com.orbitank.enums.ResourceType;
import br.com.orbitank.enums.TankStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ResourceTankRepository extends JpaRepository<ResourceTank, Long> {

    @Query("SELECT COALESCE(SUM(t.currentVolume), 0) FROM ResourceTank t WHERE t.lunarStation.id = :stationId AND t.resourceType = :type")
    Double sumVolumeByStationAndType(@Param("stationId") Long stationId, @Param("type") ResourceType type);

    boolean existsByLunarStationIdAndStatusIn(Long stationId, List<TankStatus> statuses);

    List<ResourceTank> findByLunarStationIdAndResourceType(Long stationId, ResourceType type);

    List<ResourceTank> findByLunarStationId(Long stationId);
}