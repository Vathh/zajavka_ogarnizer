package pl.ogarnizer.integration;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import pl.ogarnizer.integration.configuration.AbstractIT;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeControllerIT extends AbstractIT {

    private final TestRestTemplate testRestTemplate;

//    @Test
//    void homePageWorksCorrectly(){
//        String url = String.format("http://localhost:%s%s", port, basePath);
//
//        String page = this.testRestTemplate.getForObject(url, String.class);
//        Assertions.assertThat(page).contains("Away Works");
//        Assertions.assertThat(page).contains("Orders");
//        Assertions.assertThat(page).contains("Services");
//    }
}
