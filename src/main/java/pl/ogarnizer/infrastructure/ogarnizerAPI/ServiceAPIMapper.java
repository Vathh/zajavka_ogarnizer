package pl.ogarnizer.infrastructure.ogarnizerAPI;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ogarnizer.domain.Service;
import pl.ogarnizer.infrastructure.ogarnizerAPI.model.ServiceDTO;

import java.util.Objects;

@Component
@AllArgsConstructor
public class ServiceAPIMapper {
    private final UserAPIMapper userMapper;
    private final PriorityAPIMapper priorityMapper;
    private final ClientAPIMapper clientMapper;
    private final StageAPIMapper stageMapper;

    public Service map(ServiceDTO serviceDTO){
        return Service.builder()
                .creatingUser(userMapper.map(Objects.requireNonNull(serviceDTO.getCreatingUser())))
                .createdDate(serviceDTO.getCreatedDate())
                .priority(priorityMapper.map(Objects.requireNonNull(serviceDTO.getPriority())))
                .client(clientMapper.map(Objects.requireNonNull(serviceDTO.getClient())))
                .description(serviceDTO.getDescription())
                .device(serviceDTO.getDevice())
                .additionalInfo(serviceDTO.getAdditionalInfo())
                .updateInfo(serviceDTO.getUpdateInfo())
                .stage(stageMapper.map(Objects.requireNonNull(serviceDTO.getStage())))
                .build();
    }
}
