package pl.ogarnizer.integration.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.ogarnizer.api.dto.ClosedAwayWorkDTO;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.rest.dto.AwayWorksDTO;
import pl.ogarnizer.api.rest.dto.ClosedAwayWorksDTO;
import pl.ogarnizer.integration.configuration.RestAssuredIntegrationTestBase;
import pl.ogarnizer.integration.support.AwayWorkControllerTestSupport;
import pl.ogarnizer.integration.support.ClosedAwayWorkControllerTestSupport;
import pl.ogarnizer.util.DtoFixtures;

public class ClosedAwayWorkControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements ClosedAwayWorkControllerTestSupport, AwayWorkControllerTestSupport {

    @Test
    void thatClosedAwayWorkListCanBeRetrievedCorrectly(){
        //given
        TaskDTO taskDTO = DtoFixtures.someTaskDTO1();
        ClientDTO clientDTO = DtoFixtures.someClientDTO1();

        TaskDTO taskDTO2 = DtoFixtures.someTaskDTO2();
        ClientDTO clientDTO2 = DtoFixtures.someClientDTO2();

        ClosedAwayWorkDTO closedAwayWorkDTO = DtoFixtures.someClosedAwayWorkDTO1().withUpdateInfo(null);
        ClosedAwayWorkDTO closedAwayWorkDTO2 = DtoFixtures.someClosedAwayWorkDTO2().withUpdateInfo(null);
        //when
        addClient(clientDTO);
        addAwayWork(taskDTO);
        addClient(clientDTO2);
        addAwayWork(taskDTO2);

        AwayWorksDTO awayWorks = getAwayWorks();
        awayWorks.getAwayWorks().forEach(awayWork -> deleteAwayWork(awayWork.getAwayWorkId(),
                true, awayWork.getCreatingUserName()));

        ClosedAwayWorksDTO closedAwayWorks = getClosedAwayWorks();

        //then
        Assertions.assertThat(closedAwayWorks.getClosedAwayWorks())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("closedAwayWorkId", "createdDate", "closedDate")
                .containsExactlyInAnyOrder(closedAwayWorkDTO, closedAwayWorkDTO2);
    }

    @Test
    void thatDeletingClosedAwayWorkWorksCorrectly(){
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

        AwayWorksDTO awayWorks = getAwayWorks();
        awayWorks.getAwayWorks().forEach(awayWork -> deleteAwayWork(awayWork.getAwayWorkId(),
                true, awayWork.getCreatingUserName()));
        Integer closedAwayWorkId = getClosedAwayWorks().getClosedAwayWorks().get(0).getClosedAwayWorkId();
        deleteClosedAwayWork(closedAwayWorkId);

        ClosedAwayWorksDTO closedAwayWorks = getClosedAwayWorks();

        //then
        Assertions.assertThat(closedAwayWorks.getClosedAwayWorks()).hasSize(1);
    }
}
