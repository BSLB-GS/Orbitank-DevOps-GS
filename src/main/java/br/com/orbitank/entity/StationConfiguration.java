package br.com.orbitank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_station_configuration")
public class StationConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "station_id", nullable = false)
    private LunarStation lunarStation;

    @NotNull
    @Column(nullable = false)
    private Double minimumWaterLevel;

    @NotNull
    @Column(nullable = false)
    private Double minimumHydrogenLevel;

    @NotNull
    @Column(nullable = false)
    private Double minimumOxygenLevel;

    @NotNull
    @Column(nullable = false)
    private Double minimumEnergyLevel;

    @NotNull
    @Column(nullable = false)
    private Double maximumTankPressure;

    @NotNull
    @Column(nullable = false)
    private Double maximumTemperature;

    @NotNull
    @Column(nullable = false)
    private Double minimumRobotBattery;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}