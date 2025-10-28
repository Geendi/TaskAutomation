package reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * ExtentManager class
 * -------------------
 * Creates and manages a single ExtentReports instance used across all tests.
 * This ensures consistent and professional HTML reporting.
 */
public class ExtentManager {
    private static ExtentReports extent;
    /** Returns a singleton ExtentReports instance (creates it if not yet created). */
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance("reports/ExtentReport.html");
        }
        return extent;
    }
    /** Initializes the Extent report and sets basic configuration. */
    private static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);
        reporter.config().setReportName("Tealium Demo Automation Report");
        reporter.config().setDocumentTitle("Automation Test Results");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Project", "Tealium E-Commerce Demo");
        extent.setSystemInfo("Tester", "Your Name");
        return extent;
    }
}