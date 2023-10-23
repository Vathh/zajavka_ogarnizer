package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HomeController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureMockMvc(addFilters = false)
public class HomeControllerWebMvcTest {

    private MockMvc mockMvc;

    @Test
    public void thatHomePageWorksCorrectly() throws Exception{
        mockMvc.perform(get(HomeController.HOME))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(content().string(containsString("Away Works")))
                .andExpect(content().string(containsString("Orders")))
                .andExpect(content().string(containsString("Services")));
    }
}
