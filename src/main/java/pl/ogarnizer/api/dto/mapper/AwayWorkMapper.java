package pl.ogarnizer.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.ogarnizer.api.dto.AwayWorkDTO;
import pl.ogarnizer.domain.AwayWork;

@Mapper(componentModel = "spring")
public interface AwayWorkMapper {
    default AwayWorkDTO map(final AwayWork awayWork){
        return AwayWorkDTO.builder()
                .awayWorkId(awayWork.getAwayWorkId())
                .creatingUserName(awayWork.getCreatingUser().getUserName())
                .createdDate(awayWork.getCreatedDate())
                .priorityName(awayWork.getPriority().getName())
                .clientName(awayWork.getClient().getName())
                .description(awayWork.getDescription())
                .place(awayWork.getPlace())
                .device(awayWork.getDevice())
                .additionalInfo(awayWork.getAdditionalInfo())
                .updateInfo(awayWork.getUpdateInfo())
                .stageName(awayWork.getStage().getName())
                .build();
    }
}
