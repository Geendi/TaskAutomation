package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ShoesPage class
 * ---------------------
 * Manages navigation through "Accessories" category and filtering shoes by price.
 * Provides method to open a specific product and navigate to ProductPage.
 */
public class ShoesPage extends BasePage {

    private final By shoesTitle = By.cssSelector("div[class='page-title category-title'] h1");
    private final By dorianProduct = By.cssSelector("h2[class='product-name'] a[title='Dorian Perforated Oxford']");
    private final By sortByDropdown = By.cssSelector("div[class='toolbar-bottom'] select[title='Sort By']");

    private final By productPriceLocator = By.cssSelector("span.regular-price > span.price, p.special-price > span.price");

    public ShoesPage() {
        super();
    }

    public void verifyPricesAreSortedAscending() {
        List<Double> actualPrices = getProductPrices();

        // 2. Create a new list that is a sorted copy of the actual prices
        List<Double> expectedSortedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedSortedPrices);

        // 3. Assert that the two lists are identical
        Assert.assertEquals(actualPrices, expectedSortedPrices, "Prices are not sorted in ascending order.");
    }

    /**
     * Method to get all product prices from the page.
     */
    public List<Double> getProductPrices() {
        List<WebElement> priceElements = driver.findElements(productPriceLocator);
        List<Double> prices = new ArrayList<>();

        if (priceElements.isEmpty()) {
            Assert.fail("No product prices were found with locator: " + productPriceLocator.toString());
        }

        for (WebElement priceElement : priceElements) {
            // Get text (e.g., "$55.00" or "$45.00")
            String priceText = priceElement.getText();

            // Clean text to "55.00" or "45.00"
            String cleanedPrice = priceText.replaceAll("[^\\d.]", "");

            if (!cleanedPrice.isEmpty()) {
                prices.add(Double.parseDouble(cleanedPrice));
            }
        }
        return prices;
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
        scrollAndClick(sortByDropdown);
        selectByText(sortByDropdown, "Price");
    }

}
