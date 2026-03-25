package ca.bc.gov.open.jag.coalargefileservice.model;

public class CSOStoreDocumentRequest {

    private String filename;
    private String applicationViewGrant;
    private String appId;
    private String userName;
    private String password;
    private String databaseId;
    private String ticketLifetime;

    public CSOStoreDocumentRequest(String filename, String applicationViewGrant, String appId, String userName, String password, String databaseId, String ticketLifetime) {
        this.filename = filename;
        this.applicationViewGrant = applicationViewGrant;
        this.appId = appId;
        this.userName = userName;
        this.password = password;
        this.databaseId = databaseId;
        this.ticketLifetime = ticketLifetime;
    }

    public String getFilename() {
        return filename;
    }

    public String getApplicationViewGrant() {
        return applicationViewGrant;
    }

    public String getAppId() {
        return appId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public String getTicketLifetime() {
        return ticketLifetime;
    }

}
