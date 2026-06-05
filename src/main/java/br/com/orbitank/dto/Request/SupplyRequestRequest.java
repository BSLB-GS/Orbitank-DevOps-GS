package br.com.orbitank.dto.Request;

import br.com.orbitank.enums.SupplyRequestStatus;
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
public class SupplyRequestRequest {

    @NotNull(message = "O ID da missão é obrigatório")
    private Long missionId;

    @NotNull
    @PositiveOrZero
    private Double requestedWaterVolume;

    @NotNull
    @PositiveOrZero
    private Double requestedH2Volume;

    @NotNull
    @PositiveOrZero
    private Double requestedO2Volume;

    @NotNull
    @PastOrPresent
    private LocalDateTime requestDate;

    @Size(max = 255)
    private String denialReason;

    @NotNull(message = "O status da solicitação é obrigatório")
    private SupplyRequestStatus status;
}