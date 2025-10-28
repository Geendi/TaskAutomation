package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
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
    private final By errorMessage = By.cssSelector(".error-message");
    private final By successMessage = By.cssSelector("li[class='success-msg'] ul li span");

    // Constructor
    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /** Fills all fields and submits registration form. Returns HomePage after navigation. */
    public HomePage enterFullCredentials(String fname, String lname, String email, String password, String confirmPass) {
        scrollAndType(firstNameField, fname);
        scrollAndType(lastNameField, lname);
        scrollAndType(emailField, email);
        scrollAndType(passwordField, password);
        scrollAndType(confirmPassField, confirmPass);
        scrollAndClick(registerButton);
        return new HomePage(driver);
    }

    /** Returns same page for chaining. */
    public RegistrationPage enterFirstName(String fname) {
        scrollAndType(firstNameField, fname);
        return this;
    }

    public RegistrationPage enterLastName(String lname) {
        scrollAndType(lastNameField, lname);
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

    public RegistrationPage enterConfirmPass(String confirmPass){
        scrollAndType(confirmPassField, confirmPass);
        return this;
    }

    public RegistrationPage clickRegister() {
        scrollAndClick(registerButton);
        return this;
    }

    /** Returns visible error message text. */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public void verifySuccessMessage(String expected){
        String actual = getText(successMessage);
        Assert.assertTrue(actual.contains(expected),
                "Expected error message to contain: " + expected + " but got: " + actual);
    }
}
