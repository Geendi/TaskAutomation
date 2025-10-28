package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * HomePage class
 * ---------------
 * Represents the site landing page. Provides navigation methods to other pages
 * like Login, Registration, and Accessories. Also contains helpers to assert
 * logged-in state (e.g., presence of logout link or welcome text).
 */
public class HomePage extends BasePage {

    private final By accountMenu = By.cssSelector("a[class='skip-link skip-account'] span[class='label']");
    private final By loginLink = By.linkText("Log In");
    private final By logoutButton = By.linkText("Log Out");
    private final By registerLink = By.linkText("Register");
    private final By accessoriesLink = By.xpath("//a[contains(text(),'Accessories')]");
    private final By loggedInIndicator = By.cssSelector("div[class='page-title'] h1");

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /** Navigate to Login page by clicking the login link. */
    public LoginPage clickLoginLink() {
        click(accountMenu);
        click(loginLink);
        return new LoginPage(driver);
    }


    public void logout(){
        click(accountMenu);
        click(logoutButton);
        //return new LoginPage(driver);
    }

    /** Navigate to Registration page by clicking the register link. */
    public RegistrationPage clickRegisterLink() {
        click(accountMenu);
        click(registerLink);
        return new RegistrationPage(driver);
    }

    /** Navigate to Accessories page by clicking Accessories link. */
    public AccessoriesPage clickAccessories() {
        click(accessoriesLink);
        return new AccessoriesPage(driver);
    }

    /** Returns true if logged-in indicator is displayed (basic login assertion). */
    public boolean isUserLoggedIn() {
        return isDisplayed(loggedInIndicator);
    }

    public String assertLoggedIn(){
        return getText(loggedInIndicator);
    }
}
