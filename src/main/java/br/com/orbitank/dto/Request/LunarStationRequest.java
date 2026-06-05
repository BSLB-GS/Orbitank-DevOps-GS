package br.com.orbitank.dto.Request;

import br.com.orbitank.enums.StationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LunarStationRequest {

    @NotNull(message = "O código numérico da estação é obrigatório")
    private Long stationCode;

    @NotBlank(message = "O nome da estação é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String name;

    @NotBlank(message = "A localização da estação é obrigatória")
    private String location;

    @NotNull(message = "O status da estação é obrigatório")
    private StationStatus status;
}