package ca.bc.gov.open.jag.coalargefileservice.config;

import ca.bc.gov.open.jag.coalargefileservice.properties.BasicAuthProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.MessageFormat;

@Component
@EnableConfigurationProperties(BasicAuthProperties.class)
public class COABasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    Logger logger = LoggerFactory.getLogger(COABasicAuthenticationEntryPoint.class);
    private final BasicAuthProperties basicAuthProperties;
    private final ObjectMapper objectMapper;

    public COABasicAuthenticationEntryPoint(BasicAuthProperties basicAuthProperties, ObjectMapper objectMapper) {
        this.basicAuthProperties = basicAuthProperties;
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException {

        response.addHeader("WWW-Authenticate", MessageFormat.format("Basic realm={0}", getRealmName()));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        logger.warn("Unauthorized access attempt", authEx);

    }

    @Override
    public void afterPropertiesSet() {
        setRealmName(basicAuthProperties.getApplicationName());
        super.afterPropertiesSet();
    }

}
