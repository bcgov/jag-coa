package ca.bc.gov.open.coa.test.services;

import com.eviware.soapui.tools.SoapUITestCaseRunner;
import java.io.*;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Value("${test.username}")
    private String username;

    @Value("${test.password}")
    private String password;

    @Value("${test.api-host}")
    private String apiHost;

    public TestService() {}

    public void setAuthentication() throws IOException {
        InputStream template = getClass().getResourceAsStream("/coa-soapui-project-template.xml");
        Scanner scanner = new Scanner(template);

        File project = new File("./coa-soapui-project.xml");
        if (project.exists()) {
            project.delete();
        }
        project.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter("./coa-soapui-project.xml"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("{AUTHENTICATION_USERNAME}")) {
                line = line.replaceAll("\\{AUTHENTICATION_USERNAME}", username);
            }
            if (line.contains("{AUTHENTICATION_PASSWORD}")) {
                line = line.replaceAll("\\{AUTHENTICATION_PASSWORD}", password);
            }
            if (line.contains("{API_HOST}")) {
                line = line.replaceAll("\\{API_HOST}", apiHost);
            }
            writer.append(line + "\n");
        }
        writer.flush();
        writer.close();
        scanner.close();
    }

    public void runAllTests() throws Exception {
        try {
            // locate the project
            SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
            runner.setProjectFile("coa-soapui-project.xml");
            runner.run();
            var l = runner.getFailedTests();
        } catch (Exception ignored) {
        }
    }
}
