package pl.ogarnizer.integration.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.ServiceDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.rest.dto.ServicesDTO;
import pl.ogarnizer.integration.configuration.RestAssuredIntegrationTestBase;
import pl.ogarnizer.integration.support.ServiceControllerTestSupport;
import pl.ogarnizer.integration.support.WireMockTestSupport;
import pl.ogarnizer.util.DtoFixtures;

public class ServiceControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements ServiceControllerTestSupport, WireMockTestSupport {

    @Test
    void thatLoadRandomDataWorksCorrectly(){
        //given
        stubForLoadServices(wireMockServer);

        //when
        loadRandomServices();
        ServicesDTO services = getServices();

        //then
        Assertions.assertThat(services.getServices()).hasSize(5);
    }

    @Test
    void thatServiceListCanBeRetrievedCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();

        TaskDTO taskDTO2 = DtoFixtures.someTaskDTO2();
        ClientDTO clientDTO2 = DtoFixtures.someClientDTO2();

        ServiceDTO serviceDTO = DtoFixtures.someServiceDTO1().withUpdateInfo(null);
        ServiceDTO serviceDTO2 = DtoFixtures.someServiceDTO2().withUpdateInfo(null);
        //when
        addClient(clientDTO);
        addService(taskDTO);
        addClient(clientDTO2);
        addService(taskDTO2);

        ServicesDTO services = getServices();

        //then
        Assertions.assertThat(services.getServices())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("serviceId", "createdDate", "stageName")
                .containsExactlyInAnyOrder(serviceDTO, serviceDTO2);
    }

    @Test
    void thatServiceDetailsCanBeRetrievedCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();
        ServiceDTO serviceToCompare = DtoFixtures.someServiceDTO1().withUpdateInfo(null);

        //when
        addClient(clientDTO);
        addService(taskDTO);
        ServicesDTO services = getServices();
        Integer serviceId = services.getServices().get(0).getServiceId();

        ServiceDTO serviceDTO = serviceDetails(serviceId);

        //then
        Assertions.assertThat(serviceDTO)
                .isEqualTo(serviceToCompare);
    }

    @Test
    void thatUpdatingServiceWorksCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();
        ServiceDTO serviceToCompare = DtoFixtures.someServiceDTO1()
                .withPriorityName("high")
                .withUpdateInfo("inne informacje o aktualizacji")
                .withStageName("to_invoice");

        //when
        addClient(clientDTO);
        addService(taskDTO);
        ServicesDTO services = getServices();
        Integer serviceId = services.getServices().get(0).getServiceId();
        UpdateTaskDTO updateTaskDTO = DtoFixtures.someUpdateTaskDTO1();

        updateService(serviceId, updateTaskDTO);
        ServiceDTO serviceDTO = serviceDetails(serviceId);

        //then
        Assertions.assertThat(serviceDTO)
                .isEqualTo(serviceToCompare);
    }

    @Test
    void thatDeletingServiceWorksCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();

        TaskDTO taskDTO2 = DtoFixtures.someTaskDTO2();
        ClientDTO clientDTO2 = DtoFixtures.someClientDTO2();

        //when
        addClient(clientDTO);
        addService(taskDTO);
        addClient(clientDTO2);
        addService(taskDTO2);

        ServiceDTO serviceToDelete = getServices().getServices().get(0);
        deleteService(serviceToDelete.getServiceId(), true, "Wojtek");
        ServicesDTO services = getServices();

        //then
        Assertions.assertThat(services.getServices()).hasSize(1);
    }
}
