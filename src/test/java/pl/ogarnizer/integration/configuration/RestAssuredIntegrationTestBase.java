package pl.ogarnizer.integration.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import pl.ogarnizer.infrastructure.database.repository.jpa.AwayWorkJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.jpa.ClientJpaRepository;
import pl.ogarnizer.integration.support.ControllerTestSupport;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public abstract class RestAssuredIntegrationTestBase
        extends AbstractIT implements ControllerTestSupport {

    @Autowired
    private AwayWorkJpaRepository awayWorkJpaRepository;

    @Autowired
    private ClientJpaRepository clientJpaRepository;
    @Autowired
    protected ObjectMapper objectMapper;
    protected static WireMockServer wireMockServer;

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public RequestSpecification requestSpecification(){
        return RestAssured
                .given()
                .config(getConfig())
                .basePath(basePath)
                .port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
    }

    private RestAssuredConfig getConfig() {
        return RestAssuredConfig.config()
                .objectMapperConfig(new ObjectMapperConfig()
                        .jackson2ObjectMapperFactory((type, s) -> objectMapper));
    }

    @BeforeAll
    static void beforeAll(){
        wireMockServer = new WireMockServer(wireMockConfig()
                .port(9999)
                .extensions(new ResponseTemplateTransformer(false))
        );
        wireMockServer.start();
    }

    @BeforeEach
    void beforeEach(){
        awayWorkJpaRepository.deleteAll();
        clientJpaRepository.deleteAll();
    }

    @AfterEach
    void afterEach() {
        wireMockServer.resetAll();
    }

    @AfterAll
    static void afterAll(){
        wireMockServer.stop();
    }
}
