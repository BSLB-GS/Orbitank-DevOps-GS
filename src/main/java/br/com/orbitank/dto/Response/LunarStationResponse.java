package br.com.orbitank.dto.Response;

import br.com.orbitank.enums.StationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LunarStationResponse extends RepresentationModel<LunarStationResponse> {
    private Long id;
    private Long stationCode;
    private String name;
    private String location;
    private StationStatus status;
}