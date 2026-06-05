package br.com.orbitank.entity;

import br.com.orbitank.enums.SupplyRequestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tb_supply_request")

public class SupplyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O ID da missão é obrigatório")
    @ManyToOne
    @JoinColumn(name = "mission_id", nullable = false)
    private SpaceMission mission;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Double requestedWaterVolume;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Double requestedH2Volume;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Double requestedO2Volume;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime requestDate;

    @Size(max = 255)
    private String denialReason;

    @NotNull(message = "O status da solicitação é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SupplyRequestStatus status;
}