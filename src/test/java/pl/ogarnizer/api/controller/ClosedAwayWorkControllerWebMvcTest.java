package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.ogarnizer.api.dto.mapper.ClosedAwayWorkMapper;
import pl.ogarnizer.business.ClosedAwayWorkService;
import pl.ogarnizer.domain.ClosedAwayWork;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClosedAwayWorkController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@WithMockUser(username = "Wojtek", password = "wojtek123", authorities = { "admin" })
@AutoConfigureMockMvc(addFilters = false)
public class ClosedAwayWorkControllerWebMvcTest {

    private MockMvc mockMvc;

    @MockBean
    private final ClosedAwayWorkService closedAwayWorkService;
    @MockBean
    private final ClosedAwayWorkMapper closedAwayWorkMapper;

    @Test
    void thatClosedAwayWorkPageLoadsCorrectly() throws Exception{
        //given
        when(closedAwayWorkService.findClosedAwayWorks(any(),any())).thenReturn(new PageImpl<>(DomainFixtures.someClosedAwayWorksList()));
        when(closedAwayWorkMapper.map(any(ClosedAwayWork.class))).thenReturn(DtoFixtures.someClosedAwayWorkDTO1());

        //when, then
        mockMvc.perform(get(ClosedAwayWorkController.CLOSED_AWAY_WORK))
                .andExpect(status().isOk())
                .andExpect(view().name("closed_away_work"))
                .andExpect(model().attributeExists("closedAwayWorkDTOs"))
                .andExpect(content().string(containsString("Closed Away works")));
    }

    @Test
    void thatDeleteClosedAwayWorkRedirectsCorrectly() throws Exception {
        //given, when, then
        mockMvc.perform(delete(ClosedAwayWorkController.DELETE_CLOSED_AWAY_WORK, 1))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/closed_away_work"));
    }
}
