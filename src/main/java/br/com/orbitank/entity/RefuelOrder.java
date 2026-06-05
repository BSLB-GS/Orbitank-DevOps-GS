package br.com.orbitank.entity;

import br.com.orbitank.enums.RefuelOrderStatus;
import br.com.orbitank.enums.TankStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
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
@Table(name = "tb_refuel_order")

public class RefuelOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O pedido de suprimento é obrigatório")
    @OneToOne
    @JoinColumn(name = "supply_request_id", nullable = false)
    private SupplyRequest supplyRequest;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Double actualWaterTransferred;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Double actualH2Transferred;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Double actualO2Transferred;

    @NotNull(message = "O status da ordem é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RefuelOrderStatus status;

    @NotNull(message = "O status do tanque é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TankStatus tankStatus;
}