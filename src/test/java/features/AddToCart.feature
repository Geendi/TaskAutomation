@AddToCart
Feature: AddToCart

  Background:
    Given User is on the Login Page

  Scenario Outline: Successfully add a product to the cart after filtering
    When User enters valid credentials "<username>" and "<password>"
    And User clicks the login button
    Then User should be redirected to the account dashboard with the header "<expected>"
    When User hover accessories dropdown menu
    Then User can view accessories dropdown menu is displayed
    When User clicks on shoes section
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
      | validUser@example.com | ValidPass123 | MY DASHBOARD | SHOES | DORIAN PERFORATED OXFORD | Black          | 10            | SHOPPING CART | Dorian Perforated Oxford was added to your shopping cart. |


  Scenario Outline: User cannot proceed to checkout until the required fields are selected
    When User enters valid credentials "<username>" and "<password>"
    And User clicks the login button
    Then User should be redirected to the account dashboard with the header "<expected>"
    When User hover accessories dropdown menu
    Then User can view accessories dropdown menu is displayed
    When User clicks on shoes section
    Then User should be redirected to shoes page with "<title>" title
    And User filters the shoes in ascending order by price
    And User clicks on view details button of the product
    Then User is redirected to the product page for "<product name>"
    When User clicks the add to cart button
    Then User sees an error indicating the required fields "<error message>"

    Examples:
      | username              | password     | expected     | title | product name             | error message             |
      | validUser@example.com | ValidPass123 | MY DASHBOARD | SHOES | DORIAN PERFORATED OXFORD | This is a required field. |

