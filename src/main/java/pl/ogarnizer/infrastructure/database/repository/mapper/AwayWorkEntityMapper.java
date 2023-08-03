package pl.ogarnizer.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.infrastructure.database.entity.AwayWorkEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AwayWorkEntityMapper {

    AwayWork mapFromEntity(AwayWorkEntity entity);

    AwayWorkEntity mapToEntity(AwayWork awayWork);
}
