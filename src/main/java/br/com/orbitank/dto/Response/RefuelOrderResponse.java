package br.com.orbitank.dto.Response;

import br.com.orbitank.enums.RefuelOrderStatus;
import br.com.orbitank.enums.TankStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefuelOrderResponse {
    private Long id;
    private Long supplyRequestId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double actualWaterTransferred;
    private Double actualH2Transferred;
    private Double actualO2Transferred;
    private RefuelOrderStatus status;
    private TankStatus tankStatus;
}