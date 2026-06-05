package br.com.orbitank.entity;

import br.com.orbitank.enums.ResourceType;
import br.com.orbitank.enums.TankStatus;
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
@Table(name = "tb_resource_tank")
public class ResourceTank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private LunarStation lunarStation;

    @NotBlank(message = "O identificador é obrigatório")
    @Size(min = 3, max = 50)
    @Column(nullable = false, unique = true)
    private String identifier;

    @NotNull(message = "O tipo de recurso é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResourceType resourceType;

    @NotNull(message = "A capacidade máxima é obrigatória")
    @Positive
    @Column(nullable = false)
    private Double maxCapacity;

    @NotNull(message = "O volume atual é obrigatório")
    @PositiveOrZero
    @Column(nullable = false)
    private Double currentVolume;

    @NotNull(message = "A pressão atual é obrigatória")
    @PositiveOrZero
    @Column(nullable = false)
    private Double currentPressure;

    @NotNull(message = "A temperatura atual é obrigatória")
    @Column(nullable = false)
    private Double currentTemperature;

    @NotNull(message = "O status do tanque é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TankStatus status;
}