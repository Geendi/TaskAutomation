package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * AccessoriesPage class
 * ---------------------
 * Manages navigation through "Accessories" category and filtering shoes by price.
 * Provides method to open a specific product and navigate to ProductPage.
 */
public class AccessoriesPage extends BasePage {

    private final By accessoriesMenu = By.xpath("//a[contains(text(),'Accessories')]");
    private final By shoesSubcategory = By.xpath("//a[contains(text(),'Shoes')]");
    private final By sortDropdown = By.id("sorter");
    private final By dorianProductLink = By.xpath("//a[contains(text(),'Dorian') or contains(text(),'Dorian Shoes')]");

    public AccessoriesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /** Opens Accessories → Shoes subcategory. */
    public AccessoriesPage navigateToShoes() {
        click(accessoriesMenu);
        click(shoesSubcategory);
        return this;
    }

/*    *//** Selects sort by 'Price: Low to High'. *//*
    public AccessoriesPage sortShoesByPrice() {
        sortDropdown.sendKeys("Price: Low to High");
        return this;
    }*/

    /** Opens Dorian product details and returns ProductPage. */
    public ProductPage openDorianProduct() {
        click(dorianProductLink);
        return new ProductPage(driver);
    }
}
