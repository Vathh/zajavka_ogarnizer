package pl.ogarnizer.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.ogarnizer.domain.Stage;
import pl.ogarnizer.infrastructure.database.entity.StageEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StageEntityMapper {

    Stage mapFromEntity(StageEntity entity);
}
