package pl.ogarnizer.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import pl.ogarnizer.infrastructure.ogarnizerAPI.ApiClient;
import pl.ogarnizer.infrastructure.ogarnizerAPI.api.AwayWorkControllerApi;
import pl.ogarnizer.infrastructure.ogarnizerAPI.api.ClientControllerApi;
import pl.ogarnizer.infrastructure.ogarnizerAPI.api.OrderControllerApi;
import pl.ogarnizer.infrastructure.ogarnizerAPI.api.ServiceControllerApi;


@Configuration
public class WebClientConfiguration {

    @Value("${api.ogarnizerAPI.url}")
    private String ogarnizerAPIUrl;

    @Bean
    public ApiClient ogarnizerAPIClient(final ObjectMapper objectMapper){
        final var exchangeStrategies = ExchangeStrategies
                .builder()
                .codecs(configurer -> {
                    configurer
                            .defaultCodecs()
                            .jackson2JsonEncoder(
                                    new Jackson2JsonEncoder(
                                            objectMapper,
                                            MediaType.APPLICATION_JSON
                                    )
                            );
                    configurer
                            .defaultCodecs()
                            .jackson2JsonDecoder(
                                    new Jackson2JsonDecoder(
                                            objectMapper,
                                            MediaType.APPLICATION_JSON
                                    )
                            );
                })
                .build();

        final var webClient = WebClient.builder()
                .exchangeStrategies(exchangeStrategies)
                .build();

        ApiClient apiClient = new ApiClient(webClient);
        apiClient.setBasePath(ogarnizerAPIUrl);
        return apiClient;
    }

    @Bean
    public AwayWorkControllerApi awayWorkApi(final ObjectMapper objectMapper){
        return new AwayWorkControllerApi(ogarnizerAPIClient(objectMapper));
    }

    @Bean
    public ClientControllerApi clientApi(final ObjectMapper objectMapper){
        return new ClientControllerApi(ogarnizerAPIClient(objectMapper));
    }

    @Bean
    public OrderControllerApi orderApi(final ObjectMapper objectMapper){
        return new OrderControllerApi(ogarnizerAPIClient(objectMapper));
    }

    @Bean
    public ServiceControllerApi serviceApi(final ObjectMapper objectMapper){
        return new ServiceControllerApi(ogarnizerAPIClient(objectMapper));
    }
}
