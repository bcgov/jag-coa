package ca.bc.gov.open.jag.coalargefileservice.config;

import ca.bc.gov.open.jag.coalargefileservice.properties.BasicAuthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableConfigurationProperties(BasicAuthProperties.class)
public class SecurityConfig {

    private final COABasicAuthenticationEntryPoint authenticationEntryPoint;
    private final BasicAuthProperties basicAuthProperties;

    public SecurityConfig(COABasicAuthenticationEntryPoint authenticationEntryPoint, BasicAuthProperties basicAuthProperties) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.basicAuthProperties = basicAuthProperties;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {

        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated())
                .httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.authenticationEntryPoint(authenticationEntryPoint));

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }

    protected CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.addAllowedMethod(HttpMethod.GET);
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth

            .inMemoryAuthentication()
            .withUser(basicAuthProperties.getUsername())
            .password(passwordEncoder().encode(basicAuthProperties.getPassword()))
            .authorities(basicAuthProperties.getAuthority());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
