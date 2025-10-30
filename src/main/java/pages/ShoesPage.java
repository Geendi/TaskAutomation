package pages;

import base.BasePage;
import org.openqa.selenium.By;

/**
 * ShoesPage class
 * ---------------------
 * Manages navigation through "Accessories" category and filtering shoes by price.
 * Provides method to open a specific product and navigate to ProductPage.
 */
public class ShoesPage extends BasePage {

    private final By shoesTitle = By.cssSelector("div[class='page-title category-title'] h1");
    //private final By dorianProduct = By.xpath("//a[contains(text(),'Dorian') or contains(text(),'Dorian Shoes')]");
    private final By dorianProduct = By.cssSelector("h2[class='product-name'] a[title='Dorian Perforated Oxford']");
    private final By sortDropdown = By.cssSelector("div[class='toolbar-bottom'] select[title='Sort By']");

    public ShoesPage() {
        super();
    }


    /** Opens Dorian product details and returns ProductPage. */
    public ProductPage openDorianProduct() {
        scrollAndClick(dorianProduct);
        return new ProductPage();
    }

    public String assertShoesPage(){
        return getText(shoesTitle);
    }

    public void sortByPrice(){
        scrollAndClick(sortDropdown);
        selectByText(sortDropdown, "Price");
    }

}
