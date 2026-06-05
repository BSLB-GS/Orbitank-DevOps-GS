package br.com.orbitank.dto.Request;

import br.com.orbitank.enums.RefuelOrderStatus;
import br.com.orbitank.enums.TankStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefuelOrderRequest {

    @NotNull(message = "O ID do pedido de suprimento é obrigatório")
    private Long supplyRequestId;

    @NotNull
    @PastOrPresent
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @NotNull
    @PositiveOrZero
    private Double actualWaterTransferred;

    @NotNull
    @PositiveOrZero
    private Double actualH2Transferred;

    @NotNull
    @PositiveOrZero
    private Double actualO2Transferred;

    @NotNull(message = "O status da ordem é obrigatório")
    private RefuelOrderStatus status;

    @NotNull(message = "O status do tanque é obrigatório")
    private TankStatus tankStatus;
}