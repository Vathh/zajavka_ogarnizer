package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import pl.ogarnizer.api.dto.AwayWorkDTO;
import pl.ogarnizer.business.AwayWorkService;

import java.util.Map;

@WebMvcTest(controllers = AwayWorkController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AwayWorkControllerWebMvcTest {


}
