package ca.bc.gov.open.jag.coalargefileservice.config;

import ca.bc.gov.open.jag.coalargefileservice.properties.OrdsConfigProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Base64;

@Configuration
@EnableConfigurationProperties(OrdsConfigProperties.class)
public class RestClientConfiguration {

    private final OrdsConfigProperties ordsConfigProperties;

    public RestClientConfiguration(OrdsConfigProperties ordsConfigProperties) {
        this.ordsConfigProperties = ordsConfigProperties;
    }

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .defaultHeader("Authorization", basicAuthHeader())
                .build();
    }

    private String basicAuthHeader() {

        String auth = MessageFormat.format("{0}:{1}", ordsConfigProperties.getUsername(), ordsConfigProperties.getPassword());
        return MessageFormat.format("Basic {0}",  Base64.getMimeEncoder().encodeToString(auth.getBytes(StandardCharsets.US_ASCII)));

    }

}
