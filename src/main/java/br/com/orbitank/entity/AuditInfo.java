package br.com.orbitank.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDateTime;

@Embeddable
@Data
public class AuditInfo {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
}