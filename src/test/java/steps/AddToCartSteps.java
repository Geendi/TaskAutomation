package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.AccessoriesPage;
import pages.ProductPage;
import utils.ConfigReader;
import org.testng.Assert;

/**
 * AddToCartSteps class
 * --------------------
 * Glue code for the complex product flow, using ConfigReader for login prerequisite.
 */
public class AddToCartSteps {

    private final HomePage homePage = new HomePage();
    private AccessoriesPage accessoriesPage;
    private ProductPage productPage;

    // CREDENTIALS ARE LOADED FROM config.properties (externalized data)
    private final String VALID_USER = ConfigReader.getProperty("valid.username");
    private final String VALID_PASS = ConfigReader.getProperty("valid.password");

    @Given("A valid user is logged in")
    public void a_valid_user_is_logged_in() {
        homePage.clickLoginLink()
                .enterUsername(VALID_USER)
                .enterPassword(VALID_PASS)
                .clickLogin();
    }

    @When("User navigates to the Accessories category")
    public void user_navigates_to_the_accessories_category() {
        accessoriesPage = homePage.clickAccessories();
    }

    @When("User selects the Shoes subcategory")
    public void user_selects_the_shoes_subcategory() {
        accessoriesPage.navigateToShoes();
    }

//    @When("User filters the shoes by price from {string}")
//    public void user_filters_the_shoes_by_price_from(String sortOrder) {
//        accessoriesPage.sortShoesByPrice();
//    }

    @When("User views the details of the {string} product")
    public void user_views_the_details_of_the_product(String product) {
        productPage = accessoriesPage.openDorianProduct();
    }

    @When("User selects the product's color and size")
    public void user_selects_the_products_color_and_size() {
        productPage.selectColorAndSize();
    }

    @When("User clicks the Add to Cart button")
    public void user_clicks_the_add_to_cart_button() {
        productPage.addToCart();
    }

    @Then("The confirmation message {string} should be displayed")
    public void the_confirmation_message_should_be_displayed(String expectedMessage) {
        Assert.assertTrue(productPage.getConfirmationMessage().contains(expectedMessage), "Confirmation message not found.");
    }

/*    @Then("User should be redirected to the checkout cart page")
    public void user_should_be_redirected_to_the_checkout_cart_page() {
        Assert.assertTrue(BaseTest.getDriver().getCurrentUrl().contains("checkout/cart"),
                "Not redirected to the checkout cart page.");
    }*/
}