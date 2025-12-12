package ca.bc.gov.open.jag.coalargefileservice.properties;

import jakarta.annotation.Nonnull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "coa")
public class BasicAuthProperties {

    private String username;
    private String password;
    @Nonnull
    private String applicationName;
    @Nonnull
    private String authority;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Nonnull
    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(@Nonnull String applicationName) {
        this.applicationName = applicationName;
    }

    @Nonnull
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(@Nonnull String authority) {
        this.authority = authority;
    }

}
