package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * ProductPage class
 * -----------------
 * Manages product detail interactions like selecting color, size,
 * and adding item to cart. After adding to cart, navigation returns HomePage.
 */
public class ProductPage extends BasePage {

    private final By colorOption = By.cssSelector(".color-options .option");
    private final By sizeOption = By.cssSelector(".size-options .option");
    private final By addToCartButton = By.cssSelector("product-addtocart-button");
    private final By successMessage = By.cssSelector(".color-options .option");

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /** Selects product color and size before adding to cart. Returns same page for chaining. */
    public ProductPage selectColorAndSize() {
        click(colorOption);
        click(sizeOption);
        return this;
    }

    /** Adds product to cart and returns HomePage after navigation to reflect app flow. */
    public HomePage addToCart() {
        click(addToCartButton);
        return new HomePage(driver);
    }

    /** Returns the confirmation message displayed after adding to cart. */
    public String getConfirmationMessage() {
        return getText(successMessage);
    }
}
