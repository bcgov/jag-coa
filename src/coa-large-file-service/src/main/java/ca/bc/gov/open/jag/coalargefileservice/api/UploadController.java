package ca.bc.gov.open.jag.coalargefileservice.api;

import ca.bc.gov.open.jag.coalargefileservice.exception.COAException;
import ca.bc.gov.open.jag.coalargefileservice.model.COAResponse;
import ca.bc.gov.open.jag.coalargefileservice.model.CSOStoreDocumentRequest;
import ca.bc.gov.open.jag.coalargefileservice.model.CSOStoreDocumentResponse;
import ca.bc.gov.open.jag.coalargefileservice.properties.OrdsConfigProperties;
import ca.bc.gov.open.sftp.starter.SftpService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.UUID;

import static ca.bc.gov.open.jag.coalargefileservice.Keys.DOCUMENT_SYNC_PATH;

@RestController
@EnableWebSecurity
@EnableConfigurationProperties(OrdsConfigProperties.class)
public class UploadController {

    Logger logger = LoggerFactory.getLogger(UploadController.class);

    private final SftpService sftpService;
    private final RandomStringUtils stringUtil = new RandomStringUtils();
    private final RestClient restClient;
    private final OrdsConfigProperties ordsConfigProperties;

    public UploadController(SftpService sftpService, RestClient restClient, OrdsConfigProperties ordsConfigProperties) {
        this.sftpService = sftpService;
        this.restClient = restClient;
        this.ordsConfigProperties = ordsConfigProperties;
    }

    @PostMapping("/upload")
    public ResponseEntity<COAResponse> uploadFileStreaming(@RequestPart("filePart") MultipartFile filePart) throws IOException {

        logger.info("Upload Request Received");

        String newFileName = MessageFormat.format("{0}.pdf", stringUtil.nextAlphanumeric(17)) ;

        try {
            sftpService.put(filePart.getInputStream(), newFileName);
        } catch (Exception ex) {
            logger.error("Failed to upload to sftp", ex);
            throw new COAException(ex.getMessage());
        }

        CSOStoreDocumentRequest storeDocumentRequest = new CSOStoreDocumentRequest(
                newFileName,
                "",
                null,
                null,
                null,
                null,
                null
        );

        //Make Sync Call
        CSOStoreDocumentResponse response = restClient
                .post()
                .uri(MessageFormat.format("{0}/{1}", ordsConfigProperties.getBaseUrl(), DOCUMENT_SYNC_PATH))
                .header("Authorization", basicAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .body(storeDocumentRequest)
                .retrieve()
                .body(CSOStoreDocumentResponse.class);

        logger.info("Upload finished");
        return ResponseEntity.ok(new COAResponse(response.getDocumentGUID()));

    }

    private String basicAuthHeader() {
        String auth = MessageFormat.format("{0}:{1}", ordsConfigProperties.getUsername(), ordsConfigProperties.getPassword());
        return MessageFormat.format("Basic {0}",  Base64.getMimeEncoder().encodeToString(auth.getBytes(StandardCharsets.US_ASCII)));

    }

}
