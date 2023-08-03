package pl.ogarnizer.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.ogarnizer.domain.Priority;
import pl.ogarnizer.infrastructure.database.entity.PriorityEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriorityEntityMapper {

    Priority mapFromEntity(PriorityEntity entity);
}
