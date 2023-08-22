package pl.ogarnizer.infrastructure.database.repository.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.infrastructure.database.repository.ClientRepository;
import pl.ogarnizer.infrastructure.database.repository.jpa.ClientJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.ClientEntityMapper;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.EntityFixtures;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientRepositoryUnitTest {

    @Mock
    private ClientJpaRepository clientJpaRepository;

    @Mock
    private ClientEntityMapper clientEntityMapper;

    @InjectMocks
    private ClientRepository clientRepository;

    @Test
    void thatFindByIdWorksCorrectly(){
        //given
        Integer clientId = 1;
        Client client = DomainFixtures.someClient1();
        when(clientJpaRepository.findById(clientId)).thenReturn(Optional.of(EntityFixtures.someClientEntity1()));
        when(clientEntityMapper.mapFromEntity(any())).thenReturn(client);

        //when
        Client result = clientRepository.findById(clientId).get();

        //then
        assertThat(result).isEqualTo(DomainFixtures.someClient1());
    }
}
