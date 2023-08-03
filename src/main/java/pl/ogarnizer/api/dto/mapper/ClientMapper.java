package pl.ogarnizer.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.domain.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO map(final Client client);

    Client map(final ClientDTO clientDTO);
}
