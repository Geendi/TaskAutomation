package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner class
 * -----------------
 * The TestNG runner class for Cucumber. Configures the path to feature files,
 * step definitions (glue), and reporting plugins.
 */
@CucumberOptions(
        features = "src/test/resources/features",   // Path to the feature files
        glue = {"steps", "hooks"},                 // Path to the step definition classes
        plugin = {                                // Plugins for reporting
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }

)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Overrides scenarios method to enable parallel execution via TestNG and avoid sequential execution for test runner.
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}