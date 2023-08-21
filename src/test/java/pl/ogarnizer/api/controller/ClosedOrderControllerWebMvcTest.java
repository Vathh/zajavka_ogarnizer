package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.ogarnizer.api.dto.mapper.ClosedOrderMapper;
import pl.ogarnizer.business.ClosedOrderService;
import pl.ogarnizer.domain.ClosedOrder;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClosedOrderController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@WithMockUser(username = "Wojtek", password = "wojtek123", authorities = { "admin" })
@AutoConfigureMockMvc(addFilters = false)
public class ClosedOrderControllerWebMvcTest {

    private MockMvc mockMvc;

    @MockBean
    private final ClosedOrderService closedOrderService;
    @MockBean
    private final ClosedOrderMapper closedOrderMapper;

    @Test
    void thatClosedOrderPageLoadsCorrectly() throws Exception{
        //given
        when(closedOrderService.findClosedOrders()).thenReturn(DomainFixtures.someClosedOrdersList());
        when(closedOrderMapper.map(any(ClosedOrder.class))).thenReturn(DtoFixtures.someClosedOrderDTO1());

        //when, then
        mockMvc.perform(get(ClosedOrderController.CLOSED_ORDER))
                .andExpect(status().isOk())
                .andExpect(view().name("closed_order"))
                .andExpect(model().attributeExists("closedOrderDTOs"))
                .andExpect(content().string(containsString("Closed Orders")));
    }

    @Test
    void thatDeleteClosedOrderRedirectsCorrectly() throws Exception {
        //given, when, then
        mockMvc.perform(delete(ClosedOrderController.DELETE_CLOSED_ORDER, 1))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/closed_order"));
    }
}
