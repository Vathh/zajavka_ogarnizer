package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import pl.ogarnizer.api.dto.AwayWorkDTO;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.business.AwayWorkService;
import pl.ogarnizer.business.ClientService;

import java.util.Map;
import java.util.stream.Stream;

@WebMvcTest(controllers = AwayWorkController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientControllerWebMvcTest {

//    private MockMvc mockMvc;
//
//    @MockBean
//    private ClientService clientService;
//
//    @ParameterizedTest
//    @MethodSource
//    void thatPhoneValidationWorksCorrectly(Boolean correctPhone, String phone) throws Exception{
//        //given
//        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        Map<String, String> parametersMap = ClientDTO.builder().phoneNumber(phone).build().asMap();
//    }
//
//    public static Stream<Arguments> thatPhoneValidationWorksCorrectly() { ⑦
//        return Stream.of(
//                Arguments.of(false, "+48 504 203 260@@"),
//// ...
//                Arguments.of(true, "+48 504 203 260")
//        );
//    }
}
