package br.com.orbitank.entity;

import br.com.orbitank.enums.SensorStatus;
import br.com.orbitank.enums.SensorType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tb_sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private LunarStation lunarStation;

    @NotBlank(message = "O identificador do sensor é obrigatório")
    @Size(max = 50, message = "O identificador deve ter no máximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String identifier;

    @NotBlank(message = "A localização do sensor é obrigatória")
    @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String location;

    @NotNull(message = "O status do sensor é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SensorStatus status;

    @NotNull(message = "O tipo do sensor é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SensorType sensorType;
}