package pl.ogarnizer.api.rest.controller.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.mapper.ClientMapper;
import pl.ogarnizer.api.rest.controller.ClientRestController;
import pl.ogarnizer.api.rest.dto.ClientsDTO;
import pl.ogarnizer.business.ClientService;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientRestControllerUnitTests {

    @Mock
    private ClientService clientService;
    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientRestController clientRestController;

//    @Test
//    void thatGetClientsWorksCorrectly(){
//        //given
//        List<Client> clients = List.of(DomainFixtures.someClient1(),
//                DomainFixtures.someClient1(),DomainFixtures.someClient1());
//        when(clientService.findClients()).thenReturn(clients);
//        when(clientMapper.map((Client) any())).thenReturn(DtoFixtures.someClientDTO1());
//
//        //when
//        ClientsDTO result = clientRestController.getClients();
//
//        //then
//        assertThat(result).isEqualTo(DtoFixtures.someClientsDTO());
//    }

    @Test
    void thatClientDetailsWorksCorrectly(){
        //given
        Integer clientId = 10;
        Client client = DomainFixtures.someClient1();
        when(clientService.findByClientId(clientId)).thenReturn(client);
        when(clientMapper.map(client)).thenReturn(DtoFixtures.someClientDTO1());

        //when
        ClientDTO result = clientRestController.clientDetails(clientId);

        //then
        assertThat(result).isEqualTo(DtoFixtures.someClientDTO1());
    }
}
