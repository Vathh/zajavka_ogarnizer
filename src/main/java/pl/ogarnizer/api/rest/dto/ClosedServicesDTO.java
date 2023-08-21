package pl.ogarnizer.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ogarnizer.api.dto.ClosedServiceDTO;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClosedServicesDTO {

    private List<ClosedServiceDTO> closedServices;
}
