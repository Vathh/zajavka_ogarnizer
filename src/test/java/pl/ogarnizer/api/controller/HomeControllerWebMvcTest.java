package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.ogarnizer.business.AwayWorkService;
import pl.ogarnizer.business.OrderService;
import pl.ogarnizer.business.ServiceService;
import pl.ogarnizer.util.DomainFixtures;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HomeController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureMockMvc(addFilters = false)
public class HomeControllerWebMvcTest {

    private MockMvc mockMvc;

    @MockBean
    private AwayWorkService awayWorkService;

    @MockBean
    private final OrderService orderService;

    @MockBean
    private final ServiceService serviceService;

    @Test
    public void thatHomePageWorksCorrectly() throws Exception{
        //given
        when(awayWorkService.getAwayWorksStatistics()).thenReturn(DomainFixtures.someAwayWorkStatistics());
        when(orderService.getOrdersStatistics()).thenReturn(DomainFixtures.someOrderStatistics());
        when(serviceService.getServicesStatistics()).thenReturn(DomainFixtures.someServiceStatistics());

        //when, then
        mockMvc.perform(get(HomeController.HOME))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(content().string(containsString("Away Work")))
                .andExpect(content().string(containsString("Order")))
                .andExpect(content().string(containsString("Service")));
    }
}
