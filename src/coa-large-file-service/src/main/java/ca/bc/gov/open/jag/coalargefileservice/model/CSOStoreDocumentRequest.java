package ca.bc.gov.open.jag.coalargefileservice.model;

public class CSOStoreDocumentRequest {

    private String filename;
    private String applicationViewGrant;
    private String appId;
    private String userName;
    private String password;
    private String databaseId;
    private String ticketLifetime;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getApplicationViewGrant() {
        return applicationViewGrant;
    }

    public void setApplicationViewGrant(String applicationViewGrant) {
        this.applicationViewGrant = applicationViewGrant;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public String getTicketLifetime() {
        return ticketLifetime;
    }

    public void setTicketLifetime(String ticketLifetime) {
        this.ticketLifetime = ticketLifetime;
    }

}
