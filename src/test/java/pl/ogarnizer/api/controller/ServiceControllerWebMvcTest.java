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
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.mapper.ServiceMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.business.*;
import pl.ogarnizer.domain.Service;
import pl.ogarnizer.domain.Task;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ServiceController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@WithMockUser(username = "Wojtek", password = "wojtek123", authorities = { "admin" })
@AutoConfigureMockMvc(addFilters = false)
public class ServiceControllerWebMvcTest {

    private MockMvc mockMvc;

    @MockBean
    private ServiceService serviceService;
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
    private ServiceMapper serviceMapper;
    @MockBean
    private TaskMapper taskMapper;

    @Test
    void thatServicePageLoadsCorrectly() throws Exception{
        //given
        when(serviceService.findServices(any(),any())).thenReturn(new PageImpl<>(DomainFixtures.someServicesList()));
        when(clientService.findClients()).thenReturn(DomainFixtures.someClientsList());
        when(priorityService.findPriorities()).thenReturn(DomainFixtures.allPriorities());
        when(serviceMapper.map(any())).thenReturn(DtoFixtures.someServiceDTO1());

        //when, then
        mockMvc.perform(get(ServiceController.SERVICE))
                .andExpect(status().isOk())
                .andExpect(view().name("service"))
                .andExpect(model().attributeExists("serviceDTOs"))
                .andExpect(model().attributeExists("taskDTO"))
                .andExpect(model().attributeExists("clients"))
                .andExpect(model().attributeExists("priorities"))
                .andExpect(content().string(containsString("Load random Services")))
                .andExpect(content().string(containsString("informacje o aktualizacji")));
    }

    @Test
    void thatAddingServiceRedirectsCorrectly() throws Exception {
        //given
        when(taskMapper.map(any(TaskDTO.class))).thenReturn(DomainFixtures.someTask1());

        //when, then
        mockMvc.perform(post(ServiceController.ADD_SERVICE))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/service"));
    }

    @Test
    void thatLoadingRandomDataWorksCorrectly() throws Exception{
        //given
        when(ogarnizerAPIDAO.getServices()).thenReturn(DomainFixtures.someServicesList());
        when(clientService.findByName(any())).thenReturn(DomainFixtures.someClient1());

        //when, then
        mockMvc.perform(get(ServiceController.LOAD_RANDOM_SERVICES))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/service"));
    }

    @Test
    void thatDeleteServiceRedirectsCorrectly() throws Exception {
        //given
        when(serviceService.findServiceById(any())).thenReturn(DomainFixtures.someService1());

        //when, then
        mockMvc.perform(delete(ServiceController.CLOSE_SERVICE, 1, true))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/service"));
    }

    @Test
    void thatServiceDetailsPageLoadsCorrectly() throws Exception{
        //given
        when(serviceService.findServiceById(any())).thenReturn(DomainFixtures.someService1());
        when(taskMapper.map(any(Service.class))).thenReturn(DomainFixtures.someTask1());
        when(taskMapper.map(any(Task.class))).thenReturn(DtoFixtures.someTaskDTO1());
        when(priorityService.findPriorities()).thenReturn(DomainFixtures.allPriorities());
        when(stageService.findStages()).thenReturn(DomainFixtures.allStages());

        //when, then
        mockMvc.perform(get(ServiceController.SERVICE_DETAILS, 1))
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
    void thatUpdateServiceRedirectsCorrectly() throws Exception{
        //given
        when(serviceService.findServiceById(any())).thenReturn(DomainFixtures.someService1());

        //when, then
        mockMvc.perform(put(ServiceController.UPDATE_SERVICE, 1))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/service"));
    }
}
