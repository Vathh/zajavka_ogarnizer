package pl.ogarnizer.integration.support;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public interface WireMockTestSupport {
    default void stubForLoadAwayWorks(final WireMockServer wireMockServer){
        wireMockServer.stubFor(get(urlPathMatching("/zajavka-ogarnizerAPI/away_work"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("wiremock/away_work.json")));
    }
}
