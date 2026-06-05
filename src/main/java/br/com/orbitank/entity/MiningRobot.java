package br.com.orbitank.entity;

import br.com.orbitank.enums.RobotStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tb_mining_robot")
public class MiningRobot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private LunarStation lunarStation;

    @NotBlank(message = "A identificação é obrigatória")
    @Size(min = 3, max = 50)
    @Column(nullable = false, unique = true)
    private String identification;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Double currentVolume;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double cargoCapacity;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Double currentIceCargo;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    @Column(nullable = false)
    private Double batteryLevel;

    @NotNull(message = "O status do robô é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RobotStatus status;
}