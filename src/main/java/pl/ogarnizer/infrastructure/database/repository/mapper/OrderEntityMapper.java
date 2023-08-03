package pl.ogarnizer.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.ogarnizer.domain.Order;
import pl.ogarnizer.infrastructure.database.entity.OrderEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {

    Order mapFromEntity(OrderEntity entity);

    OrderEntity mapToEntity(Order order);
}
