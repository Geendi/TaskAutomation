package steps;

import io.cucumber.java.en.And;
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

    @When("User enters valid credentials {string} and {string}")
    public void user_enters_valid_credentials(String username, String password) {
        loginPage.enterUsername(username)
                .enterPassword(password);
    }

    @And("User clicks the login button")
    public void user_clicks_the_login_button(){
        loginPage.clickLogin();
    }

    @Then("User should be redirected to the account dashboard with the header {string}")
    public void user_should_be_redirected_to_the_account_dashboard_with_the_header(String expectedHeader) {
        Assert.assertTrue(homePage.assertLoggedIn().contains(expectedHeader),
                "Expected header not found after successful login.");
    }

    @When("User attempts login with invalid credentials {string} and {string}")
    public void user_attempts_login_with_invalid_credentials(String username, String password) {
        loginPage.enterUsername(username)
                .enterPassword(password);
    }

    @Then("an error message should be displayed {string}")
    public void the_application_should_display_the_global_error_message(String expectedError) {
        loginPage.verifyErrorMessage(expectedError);
    }
}