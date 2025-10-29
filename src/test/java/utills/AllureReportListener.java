package utills; // Or whatever package you chose

import org.testng.ISuite;
import org.testng.ISuiteListener;
import java.io.IOException;

public class AllureReportListener implements ISuiteListener {



    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Test suite finished. Generating single-file HTML report...");

        // The single-file flag is the fix for broken local reports.
        String generateCommand = "allure generate allure-results --clean -o target/allure-report --single-file";

        // The path to the permanent index.html file
        String openFile = "target\\allure-report\\index.html";

        try {
            // 1. Execute GENERATE command (wrapped in cmd /c for reliability)
            Process generateProcess = Runtime.getRuntime().exec(new String[]{"cmd", "/c", generateCommand});
            generateProcess.waitFor();

            System.out.println("Allure single-file report generated at: target/allure-report/index.html");

            // 2. Execute OPEN command (cmd /c start)
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", openFile});

        } catch (IOException | InterruptedException e) {
            System.out.println("ERROR: Could not open Allure report automatically.");
            e.printStackTrace();
        }
    }
}