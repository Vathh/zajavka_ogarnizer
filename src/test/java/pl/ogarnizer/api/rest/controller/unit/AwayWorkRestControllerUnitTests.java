package pl.ogarnizer.api.rest.controller.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ogarnizer.api.dto.AwayWorkDTO;
import pl.ogarnizer.api.dto.mapper.AwayWorkMapper;
import pl.ogarnizer.api.rest.controller.AwayWorkRestController;
import pl.ogarnizer.api.rest.dto.AwayWorksDTO;
import pl.ogarnizer.business.AwayWorkService;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AwayWorkRestControllerUnitTests {

    @Mock
    private AwayWorkService awayWorkService;

    @Mock
    private AwayWorkMapper awayWorkMapper;

    @InjectMocks
    private AwayWorkRestController awayWorkRestController;


    @Test
    void thatRetrievingAwayWorksWorksCorrectly(){
        //given
        List<AwayWork> awayWorkList = List.of(DomainFixtures.someAwayWork1(),
                DomainFixtures.someAwayWork1(),DomainFixtures.someAwayWork1());
        when(awayWorkService.findAwayWorks()).thenReturn(awayWorkList);
        when(awayWorkMapper.map(any())).thenReturn(DtoFixtures.someAwayWorkDTO1());

        //when
        AwayWorksDTO result = awayWorkRestController.getAwayWorks();

        //then
        assertThat(result).isEqualTo(DtoFixtures.someAwayWorksDTO());
    }

    @Test
    void thatRetrievingAwayWorkWorksCorrectly(){
        //given
        Integer awayWorkId = 10;
        AwayWork awayWork = DomainFixtures.someAwayWork1();

        when(awayWorkService.findAwayWorkById(awayWorkId)).thenReturn(awayWork);
        when(awayWorkMapper.map(awayWork)).thenReturn(DtoFixtures.someAwayWorkDTO1());

        //when
        AwayWorkDTO result = awayWorkRestController.awayWorkDetails(awayWorkId);

        //then
        assertThat(result).isEqualTo(DtoFixtures.someAwayWorkDTO1());
    }

}
