package br.com.orbitank.repository;

import br.com.orbitank.entity.StationConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationConfigurationRepository
        extends JpaRepository<StationConfiguration, Long> {
}