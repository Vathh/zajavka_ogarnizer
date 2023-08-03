package pl.ogarnizer.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Order;
import pl.ogarnizer.domain.Service;
import pl.ogarnizer.domain.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task map(TaskDTO taskDTO);

    TaskDTO map(Task task);

    default Task map(AwayWork awayWork){
        return Task.builder()
                .taskId(awayWork.getAwayWorkId())
                .createdByUserName(awayWork.getCreatingUser().getUserName())
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

    default Task map(Order order){
        return Task.builder()
                .taskId(order.getOrderId())
                .createdByUserName(order.getCreatingUser().getUserName())
                .createdDate(order.getCreatedDate())
                .priorityName(order.getPriority().getName())
                .clientName(order.getClient().getName())
                .description(order.getDescription())
                .device(order.getDevice())
                .additionalInfo(order.getAdditionalInfo())
                .updateInfo(order.getUpdateInfo())
                .stageName(order.getStage().getName())
                .build();
    }

    default Task map(Service service){
        return Task.builder()
                .taskId(service.getServiceId())
                .createdByUserName(service.getCreatingUser().getUserName())
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
