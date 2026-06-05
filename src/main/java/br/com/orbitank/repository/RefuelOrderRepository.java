package br.com.orbitank.repository;

import br.com.orbitank.entity.RefuelOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefuelOrderRepository
        extends JpaRepository<RefuelOrder, Long> {
}