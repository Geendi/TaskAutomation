package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import org.testng.Assert;

/**
 * LoginSteps class
 * ----------------
 * Glue code for Login. Feature, reusing POM methods and validating outcomes.
 */
public class LoginSteps {

    private final HomePage homePage = new HomePage();
    private LoginPage loginPage;

    @Given("User is on the Login Page")
    public void user_is_on_the_login_page() {
        loginPage = homePage.clickLoginLink();
    }

    @When("User logs in with username {string} and password {string}")
    public void user_logs_in_with_valid_credentials(String username, String password) {
        // Username and password come from the Gherkin Example table
        loginPage.enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    @Then("User should be redirected to the account dashboard with the header {string}")
    public void user_should_be_redirected_to_the_account_dashboard_with_the_header(String expectedHeader) {
        // Validation for successful login
        Assert.assertTrue(homePage.assertLoggedIn().contains(expectedHeader),
                "Expected header not found after successful login.");
    }

    @When("User attempts login with username {string} and password {string}")
    public void user_attempts_login_with_invalid_credentials(String username, String password) {
        // Username and password come from the Gherkin Example table
        loginPage.enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    @Then("The application should display the global error message {string}")
    public void the_application_should_display_the_global_error_message(String expectedError) {
        // Validation for failed login
        loginPage.verifyErrorMessage(expectedError);
    }
}