package br.com.orbitank.repository;

import br.com.orbitank.entity.SupplyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRequestRepository
        extends JpaRepository<SupplyRequest, Long> {
}