package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.RegistrationPage;
import org.testng.Assert;

/**
 * RegistrationSteps class
 * -----------------------
 * Glue code for Registration.feature, demonstrating data pull from Scenario Outline.
 */
public class RegistrationSteps {

    private final HomePage homePage = new HomePage();
    private RegistrationPage regPage;

    /**
     * BDD Principle: Keeping Gherkin Focused
     * For a test focusing on invalid inputs (like a short password or an invalid email), the First Name and Last Name are irrelevant noise. They must be present and valid for the system to process the form, but they do not change the outcome of the negative test.
     *
     * By using constants, we keep the Gherkin Scenario Outline clear and concise, containing only the variables that matter to the test (Email, Password, Confirm Pass, Expected Error). If we included FNAME and LNAME, the Examples table would have two identical columns repeated for every single row.*/

    // Non-variable data for valid registration fields.
    private final String FNAME = "Cucumber";
    private final String LNAME = "Test";

    @Given("I am on the Home Page")
    public void user_is_on_the_home_page() {
        // Driver is initialized and navigated to baseUrl by BaseTest @BeforeMethod
    }

    @When("User attempts registration with unique {string} and {string}")
    public void user_attempts_registration_with_unique_credentials(String email, String password, String confirmPass) {
        regPage = homePage.clickRegisterLink();

        regPage.enterFirstName(FNAME)
                .enterLastName(LNAME)
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPass(confirmPass)
                .clickRegister();
    }

    @Then("User should see the success message {string}")
    public void user_should_see_the_success_message(String expectedMessage) {
        // Reuses POM validation
        regPage.verifySuccessMessage(expectedMessage);
    }

    @When("User navigates to Registration page")
    public void user_navigates_to_registration_page() {
        regPage = homePage.clickRegisterLink();
    }

    @When("User attempts registration with invalid email {string}, password {string}, and confirm password {string}")
    public void user_attempts_registration_with_invalid_inputs(String email, String password, String confirmPass) {
        regPage.enterFirstName(FNAME)
                .enterLastName(LNAME)
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPass(confirmPass)
                .clickRegister();
    }

    @Then("The application should display the field error containing {string}")
    public void the_application_should_display_field_error_containing(String expectedError) {
        // Reuses POM validation
        regPage.verifySuccessMessage(expectedError);
    }
}