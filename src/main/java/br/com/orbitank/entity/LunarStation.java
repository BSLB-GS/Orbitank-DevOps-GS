package br.com.orbitank.entity;

import br.com.orbitank.enums.StationStatus;
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
@Table(name = "tb_lunar_station")
public class LunarStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O código numérico da estação é obrigatório")
    @Column(nullable = false, unique = true)
    private Long stationCode;

    @NotBlank(message = "O nome da estação é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "A localização da estação é obrigatória")
    @Column(nullable = false)
    private String location;

    @NotNull(message = "O status da estação é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StationStatus status;

    @Embedded
    private AuditInfo auditInfo;
}