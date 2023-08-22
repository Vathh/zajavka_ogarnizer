package pl.ogarnizer.integration.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.rest.dto.ClientsDTO;
import pl.ogarnizer.integration.configuration.RestAssuredIntegrationTestBase;
import pl.ogarnizer.integration.support.ClientControllerTestSupport;
import pl.ogarnizer.integration.support.WireMockTestSupport;
import pl.ogarnizer.util.DtoFixtures;

public class ClientControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements ClientControllerTestSupport, WireMockTestSupport {

    @Test
    void thatLoadRandomDataWorksCorrectly(){
        //given
        stubForLoadClients(wireMockServer);

        //when
        loadRandomClients();
        ClientsDTO clients = getClients();

        //then
        Assertions.assertThat(clients.getClients()).hasSize(5);
    }

    @Test
    void thatClientListCanBeRetrievedCorrectly(){
        //given
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();
        ClientDTO clientDTO2 = DtoFixtures.someClientDTO2();
        //when
        addClient(clientDTO);
        addClient(clientDTO2);

        ClientsDTO clients = getClients();

        //then
        Assertions.assertThat(clients.getClients())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("clientId")
                .containsExactlyInAnyOrder(clientDTO, clientDTO2);
    }

    @Test
    void thatClientDetailsCanBeRetrievedCorrectly(){
        //given
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();
        ClientDTO clientToCompare = DtoFixtures.someClientDTO1();

        //when
        addClient(clientDTO);
        ClientsDTO clients = getClients();
        Integer clientId = clients.getClients().get(0).getClientId();

        ClientDTO result = clientDetails(clientId);

        //then
        Assertions.assertThat(result)
                .isEqualTo(clientToCompare);
    }

    @Test
    void thatDeletingClientWorksCorrectly(){
        //given
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();
        ClientDTO clientDTO2 = DtoFixtures.someClientDTO2();

        //when
        addClient(clientDTO);
        addClient(clientDTO2);

        ClientDTO clientToDelete = getClients().getClients().get(0);
        deleteClient(clientToDelete.getClientId());
        ClientsDTO clients = getClients();

        //then
        Assertions.assertThat(clients.getClients()).hasSize(1);
    }
}
