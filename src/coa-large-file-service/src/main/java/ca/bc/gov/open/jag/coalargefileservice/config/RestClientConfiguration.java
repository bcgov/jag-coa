package ca.bc.gov.open.jag.coalargefileservice.config;

import ca.bc.gov.open.jag.coalargefileservice.properties.OrdsConfigProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(OrdsConfigProperties.class)
public class RestClientConfiguration {

    private final OrdsConfigProperties ordsConfigProperties;

    public RestClientConfiguration(OrdsConfigProperties ordsConfigProperties) {
        this.ordsConfigProperties = ordsConfigProperties;
    }

    @Bean
    public RestClient restClient() {
        return RestClient.builder().build();
    }

}
