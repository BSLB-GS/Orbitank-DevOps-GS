package br.com.orbitank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
@Table(name = "tb_sensor_reading")

public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O sensor é obrigatório")
    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    @NotNull(message = "O valor da leitura é obrigatório")
    @Column(nullable = false)
    private Double readingValue;

    @NotNull
    @PastOrPresent(message = "A data/hora da leitura não pode estar no futuro")
    @Column(nullable = false)
    private LocalDateTime timestamp;
}