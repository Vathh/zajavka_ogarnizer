package pl.ogarnizer.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ogarnizer.business.dao.AwayWorkDAO;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Task;
import pl.ogarnizer.util.DomainFixtures;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AwayWorkServiceUnitTests {

    @Mock
    private AwayWorkDAO awayWorkDAO;
    @Mock
    private UserService userService;
    @Mock
    private PriorityService priorityService;
    @Mock
    private ClientService clientService;
    @Mock
    private StageService stageService;
    @InjectMocks
    private AwayWorkService awayWorkService;

    @Test
    void thatFindAwayWorksWorksCorrectly(){
        //given
        List<AwayWork> awayWorks = List.of(DomainFixtures.someAwayWork1(),
                DomainFixtures.someAwayWork1(), DomainFixtures.someAwayWork1());
        when(awayWorkDAO.findAll()).thenReturn(awayWorks);

        //when
        List<AwayWork> result = awayWorkService.findAwayWorks();

        //then
        assertThat(result).isEqualTo(List.of(DomainFixtures.someAwayWork1(),
                DomainFixtures.someAwayWork1(), DomainFixtures.someAwayWork1()));
    }

    @Test
    void thatFindAwayWorkByIdWorksCorrectly(){
        //given
        Integer awayWorkId = 10;
        AwayWork awayWork = DomainFixtures.someAwayWork1();
        when(awayWorkDAO.findByAwayWorkId(awayWorkId)).thenReturn(Optional.of(awayWork));
        //when
        AwayWork result = awayWorkService.findAwayWorkById(awayWorkId);

        //then
        assertThat(result).isEqualTo(DomainFixtures.someAwayWork1());

    }

    @Test
    void thatPreparingAwayWorkWorksCorrectly(){
        //given
        Task task = DomainFixtures.someTask1();
        when(userService.findUserByName(task.getCreatedByUserName())).thenReturn(DomainFixtures.someUser1());
        when(priorityService.findPriority(task.getPriorityName())).thenReturn(DomainFixtures.lowPriority());
        when(clientService.findByName(task.getClientName())).thenReturn(DomainFixtures.someClient1());
        when(stageService.findStages()).thenReturn(DomainFixtures.allStages());

        //when
        AwayWork result = awayWorkService.prepareAwayWork(task);

        //then
        assertThat(result).isEqualTo(DomainFixtures.someAwayWork1());

    }
}
