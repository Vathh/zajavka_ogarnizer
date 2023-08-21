package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.mapper.ClientMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.business.ClientService;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.util.DomainFixtures;
import pl.ogarnizer.util.DtoFixtures;

import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClientController.class)
@WithMockUser(username = "Wojtek", password = "wojtek123", authorities = { "admin" })
@AllArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureMockMvc(addFilters = false)
public class ClientControllerWebMvcTest {

    private MockMvc mockMvc;

    @MockBean
    private OgarnizerAPIDAO ogarnizerAPIDAO;
    @MockBean
    private ClientService clientService;
    @MockBean
    private ClientMapper clientMapper;

    @Test
    void thatClientPageLoadsCorrectly() throws Exception {
        //given
        when(clientService.findClients()).thenReturn(DomainFixtures.someClientsList());
        when(clientMapper.map(any(Client.class))).thenReturn(DtoFixtures.someClientDTO1());

        //when, then
        mockMvc.perform(get(ClientController.CLIENT))
                .andExpect(status().isOk())
                .andExpect(view().name("client"))
                .andExpect(model().attributeExists("clientDTOs"))
                .andExpect(model().attributeExists("clientDTO"))
                .andExpect(model().attributeExists("userRole"))
                .andExpect(content().string(containsString("Load random Clients")))
                .andExpect(content().string(containsString("Poznanska 15 Poznan")));
    }

    @Test
    void thatAddingClientWorksCorrectly() throws Exception{
        //given
        when(clientMapper.map(any(ClientDTO.class))).thenReturn(DomainFixtures.someClient1());

        //when, then
        mockMvc.perform(post(ClientController.ADD_CLIENT))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/client"));
    }

    @Test
    void thatLoadingRandomDataWorksCorrectly() throws Exception {
        //given
        when(ogarnizerAPIDAO.getClients()).thenReturn(DomainFixtures.someClientsList());

        //when, then
        mockMvc.perform(get(ClientController.LOAD_RANDOM_CLIENTS))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/client"));
    }

    @Test
    void thatDeleteClientWorksCorrectly() throws Exception{
        //given, when, then
        mockMvc.perform(delete(ClientController.DELETE_CLIENT, 1))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/client"));
    }

    @ParameterizedTest
    @MethodSource
    void thatPhoneValidationWorksCorrectly(Boolean correctPhone, String phone) throws Exception{
        //given
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        Map<String, String> parametersMap = DtoFixtures.someClientDTO1().asMap();
        parametersMap.put("phoneNumber", phone);
        parametersMap.forEach(parameters::add);

        //when, then
        if (correctPhone) {
            mockMvc.perform(post(ClientController.ADD_CLIENT).params(parameters))
                    .andExpect(status().isFound())
                    .andExpect(redirectedUrl("/client"));
        } else {
            mockMvc.perform(post(ClientController.ADD_CLIENT).params(parameters))
                    .andExpect(status().isBadRequest())
                    .andExpect(model().attributeExists("errorMessage"))
                    .andExpect(model().attribute("errorMessage", Matchers.containsString(phone)))
                    .andExpect(view().name("error"));
        }
    }

    public static Stream<Arguments> thatPhoneValidationWorksCorrectly() {
        return Stream.of(
                Arguments.of(false, ""),
                Arguments.of(false, "+48 504 203 260@@"),
                Arguments.of(false, "+48.504.203.260"),
                Arguments.of(false, "+55(123) 456-78-90-"),
                Arguments.of(false, "+55(123) - 456-78-90"),
                Arguments.of(false, "504.203.260"),
                Arguments.of(false, " "),
                Arguments.of(false, "-"),
                Arguments.of(false, "()"),
                Arguments.of(false, "() + ()"),
                Arguments.of(false, "(21 7777"),
                Arguments.of(false, "+48 (21)"),
                Arguments.of(false, "+"),
                Arguments.of(false, " 1"),
                Arguments.of(false, "1"),
                Arguments.of(false, "+48 (12) 504 203 260"),
                Arguments.of(false, "+48 (12) 504-203-260"),
                Arguments.of(false, "+48(12)504203260"),
                Arguments.of(false, "555-5555-555"),
                Arguments.of(true, "504 203 260")
        );
    }

    @ParameterizedTest
    @MethodSource
    void thatNIPValidationWorksCorrectly(Boolean correctNip, String nip) throws Exception {
        //given
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        Map<String, String> parametersMap = DtoFixtures.someClientDTO1().asMap();
        parametersMap.put("nip", nip);
        parametersMap.forEach(parameters::add);

        //when, then
        if (correctNip) {
            mockMvc.perform(post(ClientController.ADD_CLIENT).params(parameters))
                    .andExpect(status().isFound())
                    .andExpect(redirectedUrl("/client"));
        } else {
            mockMvc.perform(post(ClientController.ADD_CLIENT).params(parameters))
                    .andExpect(status().isBadRequest())
                    .andExpect(model().attributeExists("errorMessage"))
                    .andExpect(model().attribute("errorMessage", Matchers.containsString(nip)))
                    .andExpect(view().name("error"));
        }
    }

    public static Stream<Arguments> thatNIPValidationWorksCorrectly() {
        return Stream.of(
                Arguments.of(false, ""),
                Arguments.of(false, "12 123 123 12@@"),
                Arguments.of(false, "1 123 123 123"),
                Arguments.of(false, "123 123 123 1"),
                Arguments.of(false, "123-123-12-12"),
                Arguments.of(false, "1232122321"),
                Arguments.of(false, "123-12-12-123"),
                Arguments.of(false, "121 15 15 123"),
                Arguments.of(false, "1"),
                Arguments.of(false, "555-5555-555"),
                Arguments.of(true, "452 452 42 24")
        );
    }

}
