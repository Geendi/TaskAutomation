package utills; // Or whatever package you chose

import org.testng.ISuite;
import org.testng.ISuiteListener;
import java.io.IOException;

public class AllureReportListener implements ISuiteListener {

    /**
     * This method is executed after the TestNG suite is finished.
     */
    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Test suite finished. Generating and opening Allure report...");

        // Command to generate the permanent report in target/site/allure-maven-plugin
        String generateCommand = "allure generate target/allure-results --clean -o target/site/allure-maven-plugin";

        // Command to open that generated report
        String openCommand = "allure open target/site/allure-maven-plugin";

        try {
            // Execute the 'generate' command
            Process generateProcess = Runtime.getRuntime().exec(generateCommand);
            // Wait for it to finish before opening
            generateProcess.waitFor();

            System.out.println("Allure report generated. Opening...");

            // Execute the 'open' command
            Runtime.getRuntime().exec(openCommand);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error opening Allure report: " + e.getMessage());
            e.printStackTrace();
        }
    }
}