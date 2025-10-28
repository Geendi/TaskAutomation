Feature: AddToCart

  Scenario: Successfully add a product to the cart after filtering
    Given A valid user is logged in
    When User navigates to the Accessories category
    And User selects the Shoes subcategory
    And User filters the shoes by price from "low to high"
    And User views the details of the "Dorian Shoes" product
    And User selects the product's color and size
    And User clicks the Add to Cart button
    Then The confirmation message "Dorian Shoes was added to your shopping cart." should be displayed
    And User should be redirected to the checkout cart page