package ca.bc.gov.open.jag.coalargefileservice.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("obj")
public class ObjStoreProperties {

    private String password;
    private String appId;
    private String dbId;
    private String username;
    private String version;
    private String ticketLifetime;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTicketLifetime() {
        return ticketLifetime;
    }

    public void setTicketLifetime(String ticketLifetime) {
        this.ticketLifetime = ticketLifetime;
    }
}
