package hooks;

import base.BasePage;
import base.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

import java.io.ByteArrayInputStream;

public class Hooks {

    private final By optIn = By.id("privacy_pref_optin");
    private final By submitButton = By.id("consent_prompt_submit");

    @Before
    public void setup() {
        ConfigReader.loadProperties();
        String browser = ConfigReader.getProperty("browser");
        int implicitWait = Integer.parseInt(ConfigReader.getProperty("implicitWait"));
        int pageLoadTimeout = Integer.parseInt(ConfigReader.getProperty("pageLoadTimeout"));
        WebDriverFactory.initializeDriver(browser, implicitWait, pageLoadTimeout);
        WebDriverFactory.getDriver().get(ConfigReader.getProperty("baseUrl"));
        BasePage basePage = new BasePage();
        basePage.acceptCookies(optIn, submitButton);
    }

    @After
    public void teardown(Scenario scenario) {
        WebDriver driver = WebDriverFactory.getDriver();

        if (driver != null) {
            // Take screenshot if failed
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                /** This is the new Allure attachment method */
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
