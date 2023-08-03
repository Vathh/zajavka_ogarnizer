package pl.ogarnizer.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.ogarnizer.api.dto.ServiceDTO;
import pl.ogarnizer.domain.Service;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    default ServiceDTO map(final Service service){
        return ServiceDTO.builder()
                .serviceId(service.getServiceId())
                .creatingUserName(service.getCreatingUser().getUserName())
                .createdDate(service.getCreatedDate())
                .priorityName(service.getPriority().getName())
                .clientName(service.getClient().getName())
                .description(service.getDescription())
                .device(service.getDevice())
                .additionalInfo(service.getAdditionalInfo())
                .updateInfo(service.getUpdateInfo())
                .stageName(service.getStage().getName())
                .build();
    }
}
