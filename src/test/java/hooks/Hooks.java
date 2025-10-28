package hooks;

import base.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

import java.io.ByteArrayInputStream;

public class Hooks {

    @Before
    public void setup() {
        // 1. Load properties from your ConfigReader
        ConfigReader.loadProperties();
        String browser = ConfigReader.getProperty("browser");
        int implicitWait = Integer.parseInt(ConfigReader.getProperty("implicitWait"));
        int pageLoadTimeout = Integer.parseInt(ConfigReader.getProperty("pageLoadTimeout"));

        // 2. Initialize the driver using your WebDriverFactory
        WebDriverFactory.initializeDriver(browser, implicitWait, pageLoadTimeout);

        // 3. Navigate to the base URL
        WebDriverFactory.getDriver().get(ConfigReader.getProperty("baseUrl"));
    }

/*    @After
    public void teardown(Scenario scenario) {
        WebDriver driver = WebDriverFactory.getDriver();

        // 1. Take a screenshot if the scenario failed
        if (scenario.isFailed() && driver != null) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "FailedScenarioScreenshot");
        }

        // 2. Quit the driver using your WebDriverFactory
        WebDriverFactory.quitDriver();
    }*/

    @After
    public void teardown(Scenario scenario) {
        WebDriver driver = WebDriverFactory.getDriver();

        if (driver != null) {
            // Take screenshot if failed
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                // This is the new Allure attachment method
                Allure.addAttachment(
                        "FailedScenarioScreenshot",
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        ".png"
                );
            }

            WebDriverFactory.quitDriver();
        }
    }
}
