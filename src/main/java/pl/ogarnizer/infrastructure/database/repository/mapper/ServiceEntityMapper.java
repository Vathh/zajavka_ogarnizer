package pl.ogarnizer.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.ogarnizer.domain.Service;
import pl.ogarnizer.infrastructure.database.entity.ServiceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceEntityMapper {

    Service mapFromEntity(ServiceEntity entity);

    ServiceEntity mapToEntity(Service service);
}
