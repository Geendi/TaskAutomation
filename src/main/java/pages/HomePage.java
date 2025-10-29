package pages;

import base.BasePage;
import org.openqa.selenium.By;

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
    private final By registerLink = By.linkText("Register");
    private final By loggedInIndicator = By.cssSelector("div[class='page-title'] h1");
    private final By accessoriesLink = By.linkText("Accessories");
    private final By shoesSubCategoryLink = By.linkText("Shoes");


    public HomePage() {
        super();
    }

    public LoginPage clickLoginLink() {
        click(accountMenu);
        click(loginLink);
        return new LoginPage();
    }

    public RegistrationPage clickRegisterLink() {
        click(accountMenu);
        click(registerLink);
        return new RegistrationPage();
    }

    public ShoesPage hoverAndClickShoes() {
        moveToElement(accessoriesLink);
        click(shoesSubCategoryLink);
        return new ShoesPage();
    }

    public String assertLoggedIn(){
        return getText(loggedInIndicator);
    }
}
