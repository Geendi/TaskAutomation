package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.RegistrationPage;
import utils.TestDataGenerator;

/**
 * RegistrationSteps class
 * -----------------------
 * Glue code for Registration. Feature, demonstrating data pull from Scenario Outline.
 */
public class RegistrationSteps {

    private final HomePage homePage = new HomePage();
    private RegistrationPage regPage;

    /**
     * BDD Principle: Keeping Gherkin Focused
     * For a test focusing on invalid inputs (like a short password or an invalid email), the First Name and Last Name are irrelevant noise. They must be present and valid for the system to process the form, but they do not change the outcome of the negative test.
     *
     * By using constants, we keep the Gherkin Scenario Outline clear and concise, containing only the variables that matter to the test (Email, Password, Confirm Pass, Expected Error). If we included firsName and lastName, the Examples table would have two identical columns repeated for every single row.*/

    // Non-variable data for valid registration fields.
    private final String firsName = "User";
    private final String lastName = "Test";

    @Given("User is on the registration page")
    public void user_is_on_the_registration_page() {
        regPage = homePage.clickRegisterLink();
    }

    @When("User attempts registration with valid {string} and {string}")
    public void user_attempts_registration_with_valid_credentials(String password, String confirmPass) {
        String uniqueEmail = TestDataGenerator.generateUniqueEmail();
        regPage.enterFirstName(firsName)
                .enterLastName(lastName)
                .enterEmail(uniqueEmail)
                .enterPassword(password)
                .enterConfirmPass(confirmPass);
    }

    @And("User clicks the register button")
    public void user_clicks_the_register_button(){
        regPage.clickRegister();
    }

    @Then("User should see the success message {string}")
    public void user_should_see_the_success_message(String expectedMessage) {
        regPage.verifySuccessMessage(expectedMessage);
    }

    @When("User navigates to Registration page")
    public void user_navigates_to_registration_page() {
        regPage = homePage.clickRegisterLink();
    }

    @When("User attempts registration with invalid email {string}, password {string}, and confirm password {string}")
    public void user_attempts_registration_with_invalid_inputs(String email, String password, String confirmPass) {
        regPage.enterFirstName(firsName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPass(confirmPass);
    }

    @Then("The application should display field error for {string} containing {string}")
    public void the_application_should_display_field_error_for_containing(String fieldName, String expectedError) {
        // Pass the 'fieldName' string to the page.
        String actualError = regPage.getErrorMessageForField(fieldName);
        Assert.assertTrue(actualError.contains(expectedError),
                "Expected error for " + fieldName + " to contain: '" + expectedError + "' but got: '" + actualError + "'");
    }

}