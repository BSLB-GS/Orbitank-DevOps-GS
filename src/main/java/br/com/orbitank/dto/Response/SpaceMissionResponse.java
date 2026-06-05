package br.com.orbitank.dto.Response;

import br.com.orbitank.entity.OperationalUser;
import br.com.orbitank.enums.MissionPriority;
import br.com.orbitank.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceMissionResponse {
    private Long id;
    private String missionCode;
    private String missionName;
    private String clientCompanyName;
    private String externalCommercialRequestCode;
    private String destination;
    private LocalDateTime scheduledLaunchDate;
    private OperationalUser createdBy;
    private MissionStatus status;
    private MissionPriority priority;
}