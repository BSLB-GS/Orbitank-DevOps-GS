package br.com.orbitank.entity;

import br.com.orbitank.enums.AlertSeverity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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
@Table(name = "tb_operational_alert")
public class OperationalAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private LunarStation lunarStation;

    @NotBlank(message = "A origem do alerta deve ser identificada")
    @Column(nullable = false)
    private String source;

    @NotBlank(message = "A mensagem do alerta é obrigatória")
    @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String message;

    @NotNull(message = "O status de atividade do alerta é obrigatório")
    @Column(nullable = false)
    private Boolean active;

    @NotNull(message = "A data de disparo é obrigatória")
    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime triggeredAt;

    private LocalDateTime resolvedAt;

    @NotNull(message = "A gravidade do alerta é obrigatória")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertSeverity severity;
}