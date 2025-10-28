package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * RegistrationPage class
 * ----------------------
 * Handles user registration actions and validations.
 * After successful registration it returns HomePage to reflect navigation.
 */
public class RegistrationPage extends BasePage {

    private final By firstNameField = By.id("firstname");
    private final By lastNameField = By.id("lastname");
    private final By emailField = By.id("email_address");
    private final By passwordField = By.id("password");
    private final By confirmPassField = By.cssSelector("#confirmation");
    private final By registerButton = By.cssSelector("button[title='Register']");
    private final By successMessage = By.cssSelector("li[class='success-msg'] ul li span");
    private final By emailError = By.id("advice-validate-email-email_address");
    private final By passwordError = By.id("advice-validate-password-password");
    private final By confirmPassError = By.id("advice-validate-cpassword-confirmation");

    public RegistrationPage() {
        super();
    }

    /**
     * Returns same page for chaining.
     */
    public RegistrationPage enterFirstName(String fName) {
        scrollAndType(firstNameField, fName);
        return this;
    }

    public RegistrationPage enterLastName(String lName) {
        scrollAndType(lastNameField, lName);
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        scrollAndType(emailField, email);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        scrollAndType(passwordField, password);
        return this;
    }

    public void enterConfirmPass(String confirmPass) {
        scrollAndType(confirmPassField, confirmPass);
    }

    public void clickRegister() {
        scrollAndClick(registerButton);
    }

    public void verifySuccessMessage(String expected) {
        String actual = getText(successMessage);
        Assert.assertTrue(actual.contains(expected),
                "Expected error message to contain: " + expected + " but got: " + actual);
    }

    /**
     * Gets the error text for a specific field.
     */
    public String getErrorMessageForField(String fieldName) {
        switch (fieldName) {
            case "emailField":
                return getText(emailError);
            case "passwordField":
                return getText(passwordError);
            case "confirmPassField":
                return getText(confirmPassError);
            default:
                // If you pass a typo from the feature file, the test will fail
                throw new IllegalArgumentException("Invalid fieldName specified: " + fieldName);
        }
    }
}
