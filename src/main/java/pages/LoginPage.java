package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * LoginPage class
 * ---------------
 * Represents the Login screen and its user actions.
 * Provides methods to enter credentials and submit the form.
 * After successful login, methods return HomePage to reflect navigation.
 */
public class LoginPage extends BasePage {

    private final By usernameField = By.id("email");
    private final By passwordField = By.id("pass");
    private final By loginButton = By.id("send2");
    private final By errorMessage = By.id("li[class='error-msg'] ul li span");

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /** Enters username into the username field. Returns the same page for chaining. */
    public LoginPage enterUsername(String username) {
        type(usernameField, username);
        return this;
    }

    /** Enters password into the password field. Returns the same page for chaining. */
    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    /** Clicks the login button to submit credentials. Returns HomePage after navigation. */
    public HomePage clickLogin() {
        scrollAndClick(loginButton);
        return new HomePage(driver);
    }

    /** Returns the displayed error message after a failed login attempt. */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public void verifyErrorMessage(String expectedText) {
        String actual = getErrorMessage();
        Assert.assertTrue(actual.contains(expectedText),
                "Expected error message to contain: " + expectedText + " but got: " + actual);
    }

}
