package br.com.orbitank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceUsageId implements Serializable {
    private Long stationId;
    private String resourceType;
    private String referenceMonth;
}
