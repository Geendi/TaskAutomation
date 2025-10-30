Feature: AddToCart

  Background:
    Given User is on the Login Page

  Scenario Outline: Successfully add a product to the cart after filtering
    When User enters valid credentials "<username>" and "<password>"
    And User clicks the login button
    Then User should be redirected to the account dashboard with the header "<expected>"
    When User navigates to Shoes via the Accessories menu
    Then User should be redirected to shoes page with "<title>" title
    And User filters the shoes in ascending order by price
    And User clicks on view details button of the product
    Then User is redirected to the product page for "<product name>"
    When User selects the product's color and size
    Then User can view the selected color "<selected color>" and size "<selected size>" displayed
    When User clicks the add to cart button
    Then User should be redirected to checkout page with "<page title>" title
    And a success message confirms the product has been added to the shopping cart "<success message>"
    Examples:
      | username              | password     | expected     | title | product name             | selected color | selected size | page title    | success message                                           |
      | validUser@example.com | ValidPass123 | MY DASHBOARD | SHOES | Dorian Perforated Oxford | Black          | 10            | SHOPPING CART | Dorian Perforated Oxford was added to your shopping cart. |