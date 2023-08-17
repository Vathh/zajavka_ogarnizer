package pl.ogarnizer.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ogarnizer.business.dao.ClientDAO;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.util.DomainFixtures;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceUnitTests {

    @Mock
    private ClientDAO clientDAO;
    @InjectMocks
    private ClientService clientService;

    @Test
    void thatFindClientsWorksCorrectly(){
        //given
        List<Client> clients = List.of(DomainFixtures.someClient1(), DomainFixtures.someClient1(),
                DomainFixtures.someClient1());
        when(clientDAO.findAll()).thenReturn(clients);

        //when
        List<Client> result = clientService.findClients();

        //then
        assertThat(result).isEqualTo(List.of(DomainFixtures.someClient1(), DomainFixtures.someClient1(),
                DomainFixtures.someClient1()));
    }

    @Test
    void thatFindByNameWorksCorrectly(){
        //given
        String name = "name";
        Client client = DomainFixtures.someClient1();
        when(clientDAO.findByName(name)).thenReturn(Optional.of(client));

        //when
        Client result = clientService.findByName(name);

        //then
        assertThat(result).isEqualTo(DomainFixtures.someClient1());
    }

    @Test
    void thatFindByClientIdWorksCorrectly(){
        //given
        Integer clientId = 10;
        Client client = DomainFixtures.someClient1();
        when(clientDAO.findById(clientId)).thenReturn(Optional.of(client));

        //when
        Client result = clientService.findByClientId(clientId);

        //then
        assertThat(result).isEqualTo(DomainFixtures.someClient1());
    }
}
