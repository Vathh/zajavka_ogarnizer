package pl.ogarnizer.infrastructure.ogarnizerAPI;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ogarnizer.domain.Order;
import pl.ogarnizer.infrastructure.ogarnizerAPI.model.OrderDTO;

import java.util.Objects;

@Component
@AllArgsConstructor
public class OrderAPIMapper {
    private final UserAPIMapper userMapper;
    private final PriorityAPIMapper priorityMapper;
    private final ClientAPIMapper clientMapper;
    private final StageAPIMapper stageMapper;

    public Order map(OrderDTO orderDTO){
        return Order.builder()
                .creatingUser(userMapper.map(Objects.requireNonNull(orderDTO.getCreatingUser())))
                .createdDate(orderDTO.getCreatedDate())
                .priority(priorityMapper.map(Objects.requireNonNull(orderDTO.getPriority())))
                .client(clientMapper.map(Objects.requireNonNull(orderDTO.getClient())))
                .description(orderDTO.getDescription())
                .device(orderDTO.getDevice())
                .additionalInfo(orderDTO.getAdditionalInfo())
                .updateInfo(orderDTO.getUpdateInfo())
                .stage(stageMapper.map(Objects.requireNonNull(orderDTO.getStage())))
                .build();
    }
}
