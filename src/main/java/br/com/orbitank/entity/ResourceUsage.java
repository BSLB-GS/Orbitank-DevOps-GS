package br.com.orbitank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Entity
@Data
@IdClass(ResourceUsageId.class)
public class ResourceUsage {
    @Id
    private Long stationId;
    @Id
    private String resourceType;
    @Id
    private String referenceMonth;

    private Double quantity;
}
