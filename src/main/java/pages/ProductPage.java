package pages;

import base.BasePage;
import org.openqa.selenium.By;

/**
 * ProductPage class
 * -----------------
 * Manages product detail interactions like selecting color, size,
 * and adding item to cart. After adding to cart, navigation returns HomePage.
 */
public class ProductPage extends BasePage {

    private final By colorOption = By.cssSelector("img[alt='Black']");
    private final By colorErrorMessage = By.id("advice-required-entry-attribute92");
    private final By sizeOption = By.id("swatch98");
    private final By sizeErrorMessage = By.id("advice-required-entry-attribute186");
    private final By addToCartButton = By.cssSelector("button[title='Add to Cart']");
    private final By addToCartButton2 = By.cssSelector(".btn-cart");
    private final By successMessage = By.cssSelector("li[class='success-msg'] ul li span");
    private final By productTitle = By.cssSelector(".h1");
    private final By selectedColorHighlighted = By.id("select_label_color");
    private final By selectedSizeHighlighted = By.id("select_label_shoe_size");

    public ProductPage() {
        super();
    }

    public void selectColor() {
        click(colorOption);
    }

    public String assertSelectedColor(){
        return getText(selectedColorHighlighted);
    }

    public void selectSize(){
        click(sizeOption);
    }

    public String assertSelectedSize(){
        return getText(selectedSizeHighlighted);
    }

    public void addToCart() {
        scrollAndClick(addToCartButton);
    }

    public String getColorErrorMessage(){
        return getText(colorErrorMessage);
    }

    public String getSizeErrorMessage(){
        return getText(sizeErrorMessage);
    }

    public String getConfirmationMessageOfAddedProduct() {
        return getText(successMessage);
    }

    public String assertProductTitle(){
        return getText(productTitle);
    }
}
