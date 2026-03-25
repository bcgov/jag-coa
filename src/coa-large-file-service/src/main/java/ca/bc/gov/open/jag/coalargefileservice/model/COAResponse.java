package ca.bc.gov.open.jag.coalargefileservice.model;

public class COAResponse {

    public COAResponse(String fileGuid) {
        this.fileGuid = fileGuid;
    }

    private String fileGuid;

    public String getFileGuid() {
        return fileGuid;
    }


}
