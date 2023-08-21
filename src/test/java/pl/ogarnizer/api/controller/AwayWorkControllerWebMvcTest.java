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
import pl.ogarnizer.api.dto.mapper.AwayWorkMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.business.*;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Task;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AwayWorkController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@WithMockUser(username = "Wojtek", password = "wojtek123", authorities = { "admin" })
@AutoConfigureMockMvc(addFilters = false)
public class AwayWorkControllerWebMvcTest {

    private MockMvc mockMvc;

    @MockBean
    private AwayWorkService awayWorkService;
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
    private AwayWorkMapper awayWorkMapper;
    @MockBean
    private TaskMapper taskMapper;

    @Test
    void thatAwayWorkPageLoadsCorrectly() throws Exception{
        //given
        when(awayWorkService.findAwayWorks()).thenReturn(DomainFixtures.someAwayWorksList());
        when(clientService.findClients()).thenReturn(DomainFixtures.someClientsList());
        when(priorityService.findPriorities()).thenReturn(DomainFixtures.allPriorities());
        when(awayWorkMapper.map(any())).thenReturn(DtoFixtures.someAwayWorkDTO1());

        //when, then
        mockMvc.perform(get(AwayWorkController.AWAY_WORK))
                .andExpect(status().isOk())
                .andExpect(view().name("away_work"))
                .andExpect(model().attributeExists("awayWorkDTOs"))
                .andExpect(model().attributeExists("taskDTO"))
                .andExpect(model().attributeExists("clients"))
                .andExpect(model().attributeExists("priorities"))
                .andExpect(content().string(containsString("Load random Away Works")))
                .andExpect(content().string(containsString("informacje o aktualizacji")));
    }

    @Test
    void thatAddingAwayWorkRedirectsCorrectly() throws Exception {
        //given
        when(taskMapper.map(any(TaskDTO.class))).thenReturn(DomainFixtures.someTask1());

        //when, then
        mockMvc.perform(post(AwayWorkController.ADD_AWAY_WORK))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/away_work"));
    }

    @Test
    void thatLoadingRandomDataWorksCorrectly() throws Exception{
        //given
        when(ogarnizerAPIDAO.getAwayWorks()).thenReturn(DomainFixtures.someAwayWorksList());
        when(clientService.findByName(any())).thenReturn(DomainFixtures.someClient1());

        //when, then
        mockMvc.perform(get(AwayWorkController.LOAD_RANDOM_AWAY_WORKS))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/away_work"));
    }

    @Test
    void thatDeleteAwayWorkRedirectsCorrectly() throws Exception {
        //given
        when(awayWorkService.findAwayWorkById(any())).thenReturn(DomainFixtures.someAwayWork1());

        //when, then
        mockMvc.perform(delete(AwayWorkController.CLOSE_AWAY_WORK, 1, true))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/away_work"));
    }

    @Test
    void thatAwayWorkDetailsPageLoadsCorrectly() throws Exception{
        //given
        when(awayWorkService.findAwayWorkById(any())).thenReturn(DomainFixtures.someAwayWork1());
        when(taskMapper.map(any(AwayWork.class))).thenReturn(DomainFixtures.someTask1());
        when(taskMapper.map(any(Task.class))).thenReturn(DtoFixtures.someTaskDTO1());
        when(priorityService.findPriorities()).thenReturn(DomainFixtures.allPriorities());
        when(stageService.findStages()).thenReturn(DomainFixtures.allStages());

        //when, then
        mockMvc.perform(get(AwayWorkController.AWAY_WORK_DETAILS, 1))
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
    void thatUpdateAwayWorkRedirectsCorrectly() throws Exception{
        //given
        when(awayWorkService.findAwayWorkById(any())).thenReturn(DomainFixtures.someAwayWork1());

        //when, then
        mockMvc.perform(put(AwayWorkController.UPDATE_AWAY_WORK, 1))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/away_work"));
    }

}
