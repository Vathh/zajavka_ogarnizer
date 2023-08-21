package pl.ogarnizer.integration.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.ogarnizer.api.dto.AwayWorkDTO;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.rest.dto.AwayWorksDTO;
import pl.ogarnizer.integration.configuration.RestAssuredIntegrationTestBase;
import pl.ogarnizer.integration.support.AwayWorkControllerTestSupport;
import pl.ogarnizer.integration.support.WireMockTestSupport;
import pl.ogarnizer.util.DtoFixtures;

public class AwayWorkControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements AwayWorkControllerTestSupport, WireMockTestSupport {
    @Test
    void thatLoadRandomDataWorksCorrectly(){
        //given
        stubForLoadAwayWorks(wireMockServer);

        //when
        loadRandomAwayWorks();
        AwayWorksDTO awayWorks = getAwayWorks();

        //then
        Assertions.assertThat(awayWorks.getAwayWorks()).hasSize(5);


    }

    @Test
    void thatAwayWorkListCanBeRetrievedCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();

        TaskDTO taskDTO2 = DtoFixtures.someTaskDTO2();
        ClientDTO clientDTO2 = DtoFixtures.someClientDTO2();

        AwayWorkDTO awayWorkDTO = DtoFixtures.someAwayWorkDTO1().withUpdateInfo(null);
        AwayWorkDTO awayWorkDTO2 = DtoFixtures.someAwayWorkDTO2().withUpdateInfo(null);
        //when
        addClient(clientDTO);
        addAwayWork(taskDTO);
        addClient(clientDTO2);
        addAwayWork(taskDTO2);

        AwayWorksDTO awayWorks = getAwayWorks();

        //then
        Assertions.assertThat(awayWorks.getAwayWorks())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("awayWorkId", "createdDate", "stageName")
                .containsExactlyInAnyOrder(awayWorkDTO, awayWorkDTO2);
    }

    @Test
    void thatAwayWorkDetailsCanBeRetrievedCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();
        AwayWorkDTO awayWorkToCompare = DtoFixtures.someAwayWorkDTO1().withUpdateInfo(null);

        //when
        addClient(clientDTO);
        addAwayWork(taskDTO);
        AwayWorksDTO awayWorks = getAwayWorks();
        Integer awayWorkId = awayWorks.getAwayWorks().get(0).getAwayWorkId();

        AwayWorkDTO awayWorkDTO = awayWorkDetails(awayWorkId);

        //then
        Assertions.assertThat(awayWorkDTO)
                .isEqualTo(awayWorkToCompare);
    }

    @Test
    void thatUpdatingAwayWorkWorksCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();
        AwayWorkDTO awayWorkToCompare = DtoFixtures.someAwayWorkDTO1()
                .withPriorityName("high")
                .withUpdateInfo("inne informacje o aktualizacji")
                .withStageName("to_invoice");

        //when
        addClient(clientDTO);
        addAwayWork(taskDTO);
        AwayWorksDTO awayWorks = getAwayWorks();
        Integer awayWorkId = awayWorks.getAwayWorks().get(0).getAwayWorkId();
        UpdateTaskDTO updateTaskDTO = DtoFixtures.someUpdateTaskDTO1();

        updateAwayWork(awayWorkId, updateTaskDTO);
        AwayWorkDTO awayWorkDTO = awayWorkDetails(awayWorkId);

        //then
        Assertions.assertThat(awayWorkDTO)
                .isEqualTo(awayWorkToCompare);
    }

    @Test
    void thatDeletingAwayWorkWorksCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();

        TaskDTO taskDTO2 = DtoFixtures.someTaskDTO2();
        ClientDTO clientDTO2 = DtoFixtures.someClientDTO2();

        //when
        addClient(clientDTO);
        addAwayWork(taskDTO);
        addClient(clientDTO2);
        addAwayWork(taskDTO2);

        AwayWorkDTO awayWorkToDelete = getAwayWorks().getAwayWorks().get(0);
        deleteAwayWork(awayWorkToDelete.getAwayWorkId(), true, "Wojtek");
        AwayWorksDTO awayWorks = getAwayWorks();

        //then
        Assertions.assertThat(awayWorks.getAwayWorks()).hasSize(1);
    }

}
