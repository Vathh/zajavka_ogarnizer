package pl.ogarnizer.integration.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.ClosedServiceDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.rest.dto.ServicesDTO;
import pl.ogarnizer.api.rest.dto.ClosedServicesDTO;
import pl.ogarnizer.integration.configuration.RestAssuredIntegrationTestBase;
import pl.ogarnizer.integration.support.ServiceControllerTestSupport;
import pl.ogarnizer.integration.support.ClosedServiceControllerTestSupport;
import pl.ogarnizer.util.DtoFixtures;

public class ClosedServiceControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements ClosedServiceControllerTestSupport, ServiceControllerTestSupport {

    @Test
    void thatClosedServiceListCanBeRetrievedCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();

        TaskDTO taskDTO2 = DtoFixtures.someTaskDTO2();
        ClientDTO clientDTO2 = DtoFixtures.someClientDTO2();

        ClosedServiceDTO closedServiceDTO = DtoFixtures.someClosedServiceDTO1().withUpdateInfo(null);
        ClosedServiceDTO closedServiceDTO2 = DtoFixtures.someClosedServiceDTO2().withUpdateInfo(null);
        //when
        addClient(clientDTO);
        addService(taskDTO);
        addClient(clientDTO2);
        addService(taskDTO2);

        ServicesDTO services = getServices();
        services.getServices().forEach(service -> deleteService(service.getServiceId(),
                true, service.getCreatingUserName()));

        ClosedServicesDTO closedServices = getClosedServices();

        //then
        Assertions.assertThat(closedServices.getClosedServices())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("closedServiceId", "createdDate", "closedDate")
                .containsExactlyInAnyOrder(closedServiceDTO, closedServiceDTO2);
    }

    @Test
    void thatDeletingClosedServiceWorksCorrectly(){
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

        ServicesDTO services = getServices();
        services.getServices().forEach(service -> deleteService(service.getServiceId(),
                true, service.getCreatingUserName()));
        Integer closedServiceId = getClosedServices().getClosedServices().get(0).getClosedServiceId();
        deleteClosedService(closedServiceId);

        ClosedServicesDTO closedServices = getClosedServices();

        //then
        Assertions.assertThat(closedServices.getClosedServices()).hasSize(1);
    }
}
