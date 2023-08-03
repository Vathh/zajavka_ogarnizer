package pl.ogarnizer.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.ogarnizer.domain.ClosedService;
import pl.ogarnizer.infrastructure.database.entity.ClosedServiceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClosedServiceEntityMapper {
    ClosedService mapFromEntity(ClosedServiceEntity entity);
    ClosedServiceEntity mapToEntity(ClosedService closedService);
}
