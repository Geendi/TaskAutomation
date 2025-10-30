package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.ShoesPage;
import pages.ProductPage;
import org.testng.Assert;

/**
 * AddToCartSteps class
 * --------------------
 * Glue code for the complex product flow, using ConfigReader for login prerequisite.
 */
public class AddToCartSteps {

    private final HomePage homePage = new HomePage();
    private ShoesPage shoesPage;
    private ProductPage productPage;

    @When("User hover accessories dropdown menu")
    public void user_hover_accessories_dropdown_menu(){
        shoesPage = homePage.hoverAccessories();
    }

    @Then("User can view accessories dropdown menu is displayed")
    public void user_can_view_accessories_dropdown_menu_is_displayed(){
        homePage.accessoriesDropdownIndicator();
    }

    @When("User clicks on shoes section")
    public void user_clicks_on_shoes_section(){
        homePage.clickShoesSection();
    }

    @Then("User should be redirected to shoes page with {string} title")
    public void user_should_be_redirected_to_shoes_page_with_title(String expectedTitle){
        Assert.assertTrue(shoesPage.assertShoesPage().contains(expectedTitle), "Wrong title");
    }

    @When("User filters the shoes in ascending order by price")
    public void user_filters_the_shoes_in_ascending_order_by_price(){
        shoesPage.sortByPrice();
    }


    @When("User clicks on view details button of the product")
    public void user_clicks_on_view_details_button_of_the_product() {
        this.productPage = shoesPage.openDorianProduct();
    }

    @Then("User is redirected to the product page for {string}")
    public void user_is_redirected_to_the_product_page_for(String productName){
        Assert.assertTrue(productPage.assertProductTitle().contains(productName), "Product name mismatch");
    }


    @When("User selects the product's color and size")
    public void user_selects_the_products_color_and_size() {
        productPage.selectColor();
        productPage.selectSize();
    }

    @Then("User can view the selected color {string} and size {string} displayed")
    public void user_can_view_the_selected_color_and_size(String color, String size){
        Assert.assertTrue(productPage.assertSelectedColor().contains(color));
        Assert.assertTrue(productPage.assertSelectedSize().contains(size));
    }

    @When("User clicks the add to cart button")
    public void user_clicks_the_add_to_cart_button() {
        productPage.addToCart();
    }

    @Then("User sees an error indicating the required fields {string}")
    public void user_sees_an_error_indicating_the_required_fields(String error){
        Assert.assertTrue(productPage.getColorErrorMessage().contains(error), "Error message not found");
        Assert.assertTrue(productPage.getSizeErrorMessage().contains(error), "Error message not found");
    }

    @Then("User should be redirected to checkout page with {string} title")
    public void user_should_be_redirected_to_checkout_page_with_title(String pageTitle){

    }

    @And("a success message confirms the product has been added to the shopping cart {string}")
    public void the_confirmation_message_should_be_displayed(String expectedMessage) {
        Assert.assertTrue(productPage.getConfirmationMessageOfAddedProduct().contains(expectedMessage), "Confirmation message not found.");
    }

    @Then("Users should be able to view products displayed in ascending order by price")
    public void the_products_should_be_displayed_in_ascending_order_by_price() {
        shoesPage.verifyPricesAreSortedAscending();
    }
}