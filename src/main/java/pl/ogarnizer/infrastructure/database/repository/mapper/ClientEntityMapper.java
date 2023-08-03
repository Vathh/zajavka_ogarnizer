package pl.ogarnizer.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.infrastructure.database.entity.ClientEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientEntityMapper {

    Client mapFromEntity(ClientEntity entity);

    ClientEntity mapToEntity(Client client);
}
