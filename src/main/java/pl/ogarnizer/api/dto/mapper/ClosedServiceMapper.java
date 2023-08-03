package pl.ogarnizer.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.ogarnizer.api.dto.ClosedServiceDTO;
import pl.ogarnizer.domain.ClosedService;
import pl.ogarnizer.domain.Service;

@Mapper(componentModel = "spring")
public interface ClosedServiceMapper {
    default ClosedServiceDTO map(final ClosedService closedService){
        return ClosedServiceDTO.builder()
                .closedServiceId(closedService.getClosedServiceId())
                .creatingUserName(closedService.getCreatingUser().getUserName())
                .createdDate(closedService.getCreatedDate())
                .clientName(closedService.getClient().getName())
                .description(closedService.getDescription())
                .device(closedService.getDevice())
                .additionalInfo(closedService.getAdditionalInfo())
                .updateInfo(closedService.getUpdateInfo())
                .closingUserName(closedService.getClosingUser().getUserName())
                .closedDate(closedService.getClosedDate())
                .success(closedService.isSuccess() ? "DONE" : "INTERRUPTED")
                .build();
    }

    default ClosedService map(final Service service){
        return ClosedService.builder()
                .creatingUser(service.getCreatingUser())
                .createdDate(service.getCreatedDate())
                .client(service.getClient())
                .description(service.getDescription())
                .device(service.getDevice())
                .additionalInfo(service.getAdditionalInfo())
                .updateInfo(service.getUpdateInfo())
                .build();
    }
}
