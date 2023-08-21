package pl.ogarnizer.infrastructure.ogarnizerAPI;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.infrastructure.ogarnizerAPI.model.AwayWorkDTO;

import java.util.Objects;

@Component
@AllArgsConstructor
public class AwayWorkAPIMapper {
    private final UserAPIMapper userMapper;
    private final PriorityAPIMapper priorityMapper;
    private final ClientAPIMapper clientMapper;
    private final StageAPIMapper stageMapper;

    public AwayWork map(AwayWorkDTO awayWorkDTO){
        return AwayWork.builder()
                .creatingUser(userMapper.map(Objects.requireNonNull(awayWorkDTO.getCreatingUser())))
                .createdDate(awayWorkDTO.getCreatedDate())
                .priority(priorityMapper.map(Objects.requireNonNull(awayWorkDTO.getPriority())))
                .client(clientMapper.map(Objects.requireNonNull(awayWorkDTO.getClient())))
                .description(awayWorkDTO.getDescription())
                .place(awayWorkDTO.getPlace())
                .device(awayWorkDTO.getDevice())
                .additionalInfo(awayWorkDTO.getAdditionalInfo())
                .updateInfo(awayWorkDTO.getUpdateInfo())
                .stage(stageMapper.map(Objects.requireNonNull(awayWorkDTO.getStage())))
                .build();
    }
}
