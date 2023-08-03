package pl.ogarnizer.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.ogarnizer.api.dto.OrderDTO;
import pl.ogarnizer.domain.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default OrderDTO map(final Order order){
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .creatingUserName(order.getCreatingUser().getUserName())
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
}
