package ca.bc.gov.open.jag.coalargefileservice.model;

import java.util.UUID;

public class COAResponse {

    public COAResponse(UUID fileGuid) {
        this.fileGuid = fileGuid;
    }

    private UUID fileGuid;

    public UUID getFileGuid() {
        return fileGuid;
    }

    public void setFileGuid(UUID fileGuid) {
        this.fileGuid = fileGuid;
    }

}
