package pl.ogarnizer.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.ogarnizer.api.dto.ClosedAwayWorkDTO;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.ClosedAwayWork;

@Mapper(componentModel = "spring")
public interface ClosedAwayWorkMapper {
    default ClosedAwayWorkDTO map(final ClosedAwayWork closedAwayWork){
        return ClosedAwayWorkDTO.builder()
                .closedAwayWorkId(closedAwayWork.getClosedAwayWorkId())
                .creatingUserName(closedAwayWork.getCreatingUser().getUserName())
                .createdDate(closedAwayWork.getCreatedDate())
                .clientName(closedAwayWork.getClient().getName())
                .description(closedAwayWork.getDescription())
                .place(closedAwayWork.getPlace())
                .device(closedAwayWork.getDevice())
                .additionalInfo(closedAwayWork.getAdditionalInfo())
                .updateInfo(closedAwayWork.getUpdateInfo())
                .closingUserName(closedAwayWork.getClosingUser().getUserName())
                .closedDate(closedAwayWork.getClosedDate())
                .success(closedAwayWork.isSuccess() ? "DONE" : "INTERRUPTED")
                .build();
    }

    default ClosedAwayWork map(final AwayWork awayWork){
        return ClosedAwayWork.builder()
                .creatingUser(awayWork.getCreatingUser())
                .createdDate(awayWork.getCreatedDate())
                .client(awayWork.getClient())
                .description(awayWork.getDescription())
                .place(awayWork.getPlace())
                .device(awayWork.getDevice())
                .additionalInfo(awayWork.getAdditionalInfo())
                .updateInfo(awayWork.getUpdateInfo())
                .build();
    }
}
