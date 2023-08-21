package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.mapper.OrderMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.business.*;
import pl.ogarnizer.domain.Order;
import pl.ogarnizer.domain.Task;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = OrderController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@WithMockUser(username = "Wojtek", password = "wojtek123", authorities = { "admin" })
@AutoConfigureMockMvc(addFilters = false)
public class OrderControllerWebMvcTest {

    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;
    @MockBean
    private PriorityService priorityService;
    @MockBean
    private ClientService clientService;
    @MockBean
    private StageService stageService;
    @MockBean
    private ClosingTaskService closingTaskService;
    @MockBean
    private OgarnizerAPIDAO ogarnizerAPIDAO;
    @MockBean
    private OrderMapper orderMapper;
    @MockBean
    private TaskMapper taskMapper;

    @Test
    void thatOrderPageLoadsCorrectly() throws Exception{
        //given
        when(orderService.findOrders()).thenReturn(DomainFixtures.someOrdersList());
        when(clientService.findClients()).thenReturn(DomainFixtures.someClientsList());
        when(priorityService.findPriorities()).thenReturn(DomainFixtures.allPriorities());
        when(orderMapper.map(any())).thenReturn(DtoFixtures.someOrderDTO1());

        //when, then
        mockMvc.perform(get(OrderController.ORDER))
                .andExpect(status().isOk())
                .andExpect(view().name("order"))
                .andExpect(model().attributeExists("orderDTOs"))
                .andExpect(model().attributeExists("taskDTO"))
                .andExpect(model().attributeExists("clients"))
                .andExpect(model().attributeExists("priorities"))
                .andExpect(content().string(containsString("Load random Orders")))
                .andExpect(content().string(containsString("informacje o aktualizacji")));
    }

    @Test
    void thatAddingOrderRedirectsCorrectly() throws Exception {
        //given
        when(taskMapper.map(any(TaskDTO.class))).thenReturn(DomainFixtures.someTask1());

        //when, then
        mockMvc.perform(post(OrderController.ADD_ORDER))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/order"));
    }

    @Test
    void thatLoadingRandomDataWorksCorrectly() throws Exception{
        //given
        when(ogarnizerAPIDAO.getOrders()).thenReturn(DomainFixtures.someOrdersList());
        when(clientService.findByName(any())).thenReturn(DomainFixtures.someClient1());

        //when, then
        mockMvc.perform(get(OrderController.LOAD_RANDOM_ORDERS))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/order"));
    }

    @Test
    void thatDeleteOrderRedirectsCorrectly() throws Exception {
        //given
        when(orderService.findOrderById(any())).thenReturn(DomainFixtures.someOrder1());

        //when, then
        mockMvc.perform(delete(OrderController.CLOSE_ORDER, 1, true))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/order"));
    }

    @Test
    void thatOrderDetailsPageLoadsCorrectly() throws Exception{
        //given
        when(orderService.findOrderById(any())).thenReturn(DomainFixtures.someOrder1());
        when(taskMapper.map(any(Order.class))).thenReturn(DomainFixtures.someTask1());
        when(taskMapper.map(any(Task.class))).thenReturn(DtoFixtures.someTaskDTO1());
        when(priorityService.findPriorities()).thenReturn(DomainFixtures.allPriorities());
        when(stageService.findStages()).thenReturn(DomainFixtures.allStages());

        //when, then
        mockMvc.perform(get(OrderController.ORDER_DETAILS, 1))
                .andExpect(status().isOk())
                .andExpect(view().name("task_details"))
                .andExpect(model().attributeExists("taskDTO"))
                .andExpect(model().attributeExists("updateTaskDTO"))
                .andExpect(model().attributeExists("priorities"))
                .andExpect(model().attributeExists("stages"))
                .andExpect(model().attributeExists("taskType"))
                .andExpect(content().string(containsString("Task Details")))
                .andExpect(content().string(containsString("informacje o aktualizacji")));
    }

    @Test
    void thatUpdateOrderRedirectsCorrectly() throws Exception{
        //given
        when(orderService.findOrderById(any())).thenReturn(DomainFixtures.someOrder1());

        //when, then
        mockMvc.perform(put(OrderController.UPDATE_ORDER, 1))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/order"));
    }
}
