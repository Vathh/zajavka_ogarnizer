package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.ogarnizer.api.dto.mapper.ClosedServiceMapper;
import pl.ogarnizer.business.ClosedServiceService;
import pl.ogarnizer.domain.ClosedService;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClosedServiceController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@WithMockUser(username = "Wojtek", password = "wojtek123", authorities = { "admin" })
@AutoConfigureMockMvc(addFilters = false)
public class ClosedServiceControllerWebMvcTest {

    private MockMvc mockMvc;

    @MockBean
    private final ClosedServiceService closedServiceService;
    @MockBean
    private final ClosedServiceMapper closedServiceMapper;

    @Test
    void thatClosedServicePageLoadsCorrectly() throws Exception{
        //given
        when(closedServiceService.findClosedServices()).thenReturn(DomainFixtures.someClosedServicesList());
        when(closedServiceMapper.map(any(ClosedService.class))).thenReturn(DtoFixtures.someClosedServiceDTO1());

        //when, then
        mockMvc.perform(get(ClosedServiceController.CLOSED_SERVICE))
                .andExpect(status().isOk())
                .andExpect(view().name("closed_service"))
                .andExpect(model().attributeExists("closedServiceDTOs"))
                .andExpect(content().string(containsString("Closed Services")));
    }

    @Test
    void thatDeleteClosedServiceRedirectsCorrectly() throws Exception {
        //given, when, then
        mockMvc.perform(delete(ClosedServiceController.DELETE_CLOSED_SERVICE, 1))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/closed_service"));
    }
}
