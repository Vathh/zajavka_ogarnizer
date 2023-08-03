package pl.ogarnizer.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.ogarnizer.api.dto.ClosedOrderDTO;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.ClosedAwayWork;
import pl.ogarnizer.domain.ClosedOrder;
import pl.ogarnizer.domain.Order;

@Mapper(componentModel = "spring")
public interface ClosedOrderMapper {
    default ClosedOrderDTO map(final ClosedOrder closedOrder){
        return ClosedOrderDTO.builder()
                .closedOrderId(closedOrder.getClosedOrderId())
                .creatingUserName(closedOrder.getCreatingUser().getUserName())
                .createdDate(closedOrder.getCreatedDate())
                .clientName(closedOrder.getClient().getName())
                .description(closedOrder.getDescription())
                .device(closedOrder.getDevice())
                .additionalInfo(closedOrder.getAdditionalInfo())
                .updateInfo(closedOrder.getUpdateInfo())
                .closingUserName(closedOrder.getClosingUser().getUserName())
                .closedDate(closedOrder.getClosedDate())
                .success(closedOrder.isSuccess() ? "DONE" : "INTERRUPTED")
                .build();
    }

    default ClosedOrder map(final Order order){
        return ClosedOrder.builder()
                .creatingUser(order.getCreatingUser())
                .createdDate(order.getCreatedDate())
                .client(order.getClient())
                .description(order.getDescription())
                .device(order.getDevice())
                .additionalInfo(order.getAdditionalInfo())
                .updateInfo(order.getUpdateInfo())
                .build();
    }
}
