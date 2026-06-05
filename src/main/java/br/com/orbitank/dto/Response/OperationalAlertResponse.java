package br.com.orbitank.dto.Response;

import br.com.orbitank.enums.AlertSeverity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationalAlertResponse {
    private Long id;
    private Long lunarStationId;
    private String source;
    private String message;
    private Boolean active;
    private LocalDateTime triggeredAt;
    private LocalDateTime resolvedAt;
    private AlertSeverity severity;
}