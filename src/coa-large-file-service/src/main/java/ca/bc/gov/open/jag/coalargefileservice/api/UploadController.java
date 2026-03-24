package ca.bc.gov.open.jag.coalargefileservice.api;

import ca.bc.gov.open.jag.coalargefileservice.exception.COAException;
import ca.bc.gov.open.jag.coalargefileservice.model.COAResponse;
import ca.bc.gov.open.sftp.starter.SftpService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

@RestController
@EnableWebSecurity
public class UploadController {

    Logger logger = LoggerFactory.getLogger(UploadController.class);

    private final SftpService sftpService;
    private final RandomStringUtils stringUtil = new RandomStringUtils();

    public UploadController(SftpService sftpService) {
        this.sftpService = sftpService;
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

        //Make Sync Call

        logger.info("Upload finished");
        return ResponseEntity.ok(new COAResponse(UUID.randomUUID()));

    }

}
