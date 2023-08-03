package pl.ogarnizer.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.ogarnizer.domain.ClosedOrder;
import pl.ogarnizer.infrastructure.database.entity.ClosedOrderEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClosedOrderEntityMapper {

    ClosedOrder mapFromEntity(ClosedOrderEntity entity);

    ClosedOrderEntity mapToEntity(ClosedOrder closedOrder);
}
