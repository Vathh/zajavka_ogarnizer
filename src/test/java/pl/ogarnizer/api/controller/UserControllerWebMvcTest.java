package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.ogarnizer.api.dto.UserDTO;
import pl.ogarnizer.api.dto.mapper.RoleMapper;
import pl.ogarnizer.api.dto.mapper.UserMapper;
import pl.ogarnizer.business.RoleService;
import pl.ogarnizer.business.UserService;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
@WithMockUser(username = "Wojtek", password = "wojtek123", authorities = { "admin" })
@AllArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerWebMvcTest {

    private MockMvc mockMvc;

    @MockBean
    private final UserService userService;
    @MockBean
    private final RoleService roleService;
    @MockBean
    private final UserMapper userMapper;
    @MockBean
    private final RoleMapper roleMapper;

    @Test
    void thatUserPageLoadsCorrectly() throws Exception {
        //given
        when(userService.findUsers()).thenReturn(DomainFixtures.someUsersList());
        when(userMapper.map(any(User.class))).thenReturn(DtoFixtures.someUserDTO1());

        //when, then
        mockMvc.perform(get(UserController.USER))
                .andExpect(status().isOk())
                .andExpect(view().name("user"))
                .andExpect(model().attributeExists("userDTOs"))
                .andExpect(model().attributeExists("userDTO"))
                .andExpect(model().attributeExists("roleDTOs"))
                .andExpect(content().string(containsString("Users")))
                .andExpect(content().string(containsString("Add User")));
    }

    @Test
    void thatAddingUserWorksCorrectly() throws Exception{
        //given, when, then
        mockMvc.perform(post(UserController.ADD_USER))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/user"));
    }

    @Test
    void thatDeleteUserWorksCorrectly() throws Exception{
        //given, when, then
        mockMvc.perform(delete(UserController.DELETE_USER, 1))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/user"));
    }
}
