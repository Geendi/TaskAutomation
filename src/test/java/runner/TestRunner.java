package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestRunner class
 * -----------------
 * The TestNG runner class for Cucumber. Configures the path to feature files,
 * step definitions (glue), and reporting plugins.
 */
@CucumberOptions(
        features = "src/test/java/features",   // Path to the feature files
        glue = {"steps", "hooks"},                 // Path to the step definition classes
        plugin = {                                // Plugins for reporting
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        /** Read the tag dynamically from the system property */
        tags = "${cucumber.filter.tags}"

)
public class TestRunner extends AbstractTestNGCucumberTests {
}