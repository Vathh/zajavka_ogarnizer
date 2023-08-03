package pl.ogarnizer.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.ogarnizer.domain.ClosedAwayWork;
import pl.ogarnizer.infrastructure.database.entity.ClosedAwayWorkEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClosedAwayWorkEntityMapper {
    ClosedAwayWork mapFromEntity(ClosedAwayWorkEntity entity);
    ClosedAwayWorkEntity mapToEntity(ClosedAwayWork closedAwayWork);
}
